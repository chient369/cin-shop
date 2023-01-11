package com.cinshop.customer;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;
import com.cinshop.utility.Utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
	private CustomerService service;
	
	@Autowired
	MessagerHelper helper;
	
	@Autowired
	AuthStorage authStorage;
	
	//新規登録画面を表示
	@GetMapping("/register")
	public String registerGet(Model model) {	
		model.addAttribute("customer", new Customer());
		return "register";
	}

	//顧客情報登録
	@PostMapping("/register")
	public String registerPost(Model model, HttpServletRequest request, @ModelAttribute Customer customer) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		String email = customer.getEmail();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String phoneNumber = customer.getPhoneNumber();
		
		//フォームの値を取得
		String gender = request.getParameter("gender");
		String postCode = request.getParameter("postCode");
		String firstAddress = request.getParameter("firstAddress");
		String lastAddress = request.getParameter("lastAddress");
		
		//email検索
		Optional<Customer> cust = service.findCustomerByEmail(email);
		
		//email検索結果あり
		if (!cust.isEmpty()) {
			model.addAttribute("firstNameValue", firstName);
			model.addAttribute("lastNameValue", lastName);
			model.addAttribute("phoneNumberValue", phoneNumber);
			model.addAttribute("postCodeValue", postCode);
			model.addAttribute("firstAddressValue", firstAddress);
			model.addAttribute("lastAddressValue", lastAddress);
			model.addAttribute("emailSearchResult", "true");
			model.addAttribute("customer", new Customer());
			return "register";
		//email検索結果なし。顧客情報登録
		} else {
			Sex sex = new Sex();
			Address address = new Address();
			
			//パスワードハッシュ化
			String encodePassword = passwordEncoder.encode(customer.getPassword());
			
			//性別をセットする
			if (gender != null) {
				if (gender.equals("1")) {
					sex.setSex_id(1);
					sex.setSexName("男");
				} else if (gender.equals("2")){
					sex.setSex_id(2);
					sex.setSexName("女");
				} else {
					sex.setSex_id(0);
					sex.setSexName("未登録");
				}
				customer.setSex(sex);
			}
			
			customer.setPassword(encodePassword);
			customer.setEnable((byte)1);
			customer.setPoint(0);
			customer.setImage("img/xxx.jpg");	//あとでかえるかも
			customer.setRole("ROLE_USER");
			
			address.setPostCode(postCode);
			address.setFirstAddress(firstAddress);
			address.setLastAddress(lastAddress);
			customer.setAddress(address);
			
			//顧客情報をDBに登録
			service.save(customer);
			
			return "redirect:/login";
		}
	}
	
	
	//ログインページ表示
	@GetMapping("/login")
	public String login() {	  
		return "login";
	}
	
	
	//認証後のユーザーテスト用ページ表示
	@GetMapping("/testDebug")
	public String testDebug() {	  
		return "testDebug";
	}
	
	
	//パスワード忘れた人用のemail入力フォーム画面表示
	@GetMapping("/inputEmail")
	public String inputEmail() {	  
		return "inputEmail";
	}
	
	
	//ランダム文字列の関連付け。
	@PostMapping("/forgotPass")
	public String changePassRequest(Model model, String email) {
		
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String authCode = "";
		for (int i = 0; i < 30; i++) {
			authCode = authCode + alphabet.charAt(r.nextInt(alphabet.length()));
		}
		
		//email検索
		Optional<Customer> cust = service.findCustomerByEmail(email);
		
		//custIdと関連付けたランダム文字列を保存
		if (!cust.isEmpty()) {
			AuthStorage.getInstance(String.valueOf(cust.get().getId()), authCode);
			model.addAttribute("email", email);
			
			//URLを送信する。
			helper.sendEmail(email, "http://localhost:8085/cinshop/rstp?custId=" + cust.get().getId() + "&auth=" + authCode);
		} else {
			model.addAttribute("email", "");
		}
		return "changePassword";
	}


	//メールアドレスからユーザーがリンクを踏んだときの画面（パスワード変更フォーム画面）を表示する。
	@GetMapping("/rstp")
	public String resetPasswordGet(Model model, @RequestParam("custId") String id, @RequestParam("auth") String auth) {
		
		String code = authStorage.getCode(id);
		
		//idの検索結果が0件ではない、かつauthが一致
		if (code != null && auth.equals(code)) {
			model.addAttribute("custId", id);
			return "password-edit";
		} else {
			//無効なリクエスト
			System.out.println("dubug-no");
			return "login";
		}
	}
	

	//パスワード更新
	@PostMapping("/rstp")
	public String resetPassword(String custId, String newPassword) {
		
		//パスワードハッシュ化
		String newPass = passwordEncoder.encode(newPassword);
		
		System.out.println(newPassword);
		System.out.println(newPass);
		service.update(Integer.valueOf(custId), newPass);
		
		authStorage.remove(custId);
		return "redirect:/login";
	}
	
	
	//ユーザー情報更新ページ
	@GetMapping("/account")
	public String accountDetail(Model model) {	
		
		//スレッドローカルからSecurityContextを取得
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		//SecurityContextからAuthenticationを取得
		Authentication authentication = securityContext.getAuthentication();
		
		//AuthenticationからUserDetailsを取得（キャストが必要）
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		
		
		Optional<Customer> cust = service.findCustomerByEmail(loginUserDetails.getUsername());
		
		//テキストボックスの初期値用にセットする
		model.addAttribute("customer", new Customer());
		model.addAttribute("custId", cust.get().getId());
		model.addAttribute("emailDBValue", cust.get().getEmail());
		model.addAttribute("passwordDBValue", cust.get().getPassword());
		if (cust.get().getSex().getSex_id() != 0) {
			model.addAttribute("sexDBValue", cust.get().getSex().getSex_id());
		} else {
			model.addAttribute("sexDBValue", "0");
		}
		model.addAttribute("firstNameDBValue", cust.get().getFirstName());
		model.addAttribute("lastNameDBValue", cust.get().getLastName());
		model.addAttribute("phoneNumberDBValue", cust.get().getPhoneNumber());
		model.addAttribute("postCodeDBValue", cust.get().getAddress().getPostCode());
		model.addAttribute("firstAddressDBValue", cust.get().getAddress().getFirstAddress());
		model.addAttribute("lastAddressDBValue", cust.get().getAddress().getLastAddress());
		return "account-detail";
	}
	
	
	@PostMapping("/account/u")
	public String updateAccount(@ModelAttribute Customer customer, Model model, HttpServletRequest request) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		
		//フォームの値を取得
		String custId = request.getParameter("custId");
		String gender = request.getParameter("gender");
		String postCode = request.getParameter("postCode");
		String firstAddress = request.getParameter("firstAddress");
		String lastAddress = request.getParameter("lastAddress");
		
		//顧客情報登録
		Sex sex = new Sex();
		Address address = new Address();
		//パスワードハッシュ化
		String encodePassword = passwordEncoder.encode(customer.getPassword());
		
		//性別をセットする
		if (gender != null) {
			if (gender.equals("1")) {
				sex.setSex_id(1);
				sex.setSexName("男");
			} else if (gender.equals("2")){
				sex.setSex_id(2);
				sex.setSexName("女");
			} else {
				sex.setSex_id(0);
				sex.setSexName("未登録");
			}
			customer.setSex(sex);
		}
		
		customer.setId(Integer.valueOf(custId));
		customer.setPassword(encodePassword);
		customer.setEnable((byte)1);
		customer.setPoint(0);
		customer.setImage("img/xxx.jpg");	//あとでかえるかも
		customer.setRole("ROLE_USER");
		

		//アドレスをセットする
		//address.setCustomer(customer);
		address.setPostCode(postCode);
		address.setFirstAddress(firstAddress);
		address.setLastAddress(lastAddress);
		Address savedAddr = service.saveAddress(address);
		
		logger.info("{}がアカウントを登録しました",customer.getFullName());
		
		return "login";
	}
	
	@GetMapping("/c")
	public String accountDetail(Model model,HttpServletRequest request) {
		Customer customer = getAuthenticatedCustomer(request);
		model.addAttribute("cust",customer);
		return "account-detail";
	}
	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		Customer customer = null;
		if (email != null)
			customer = service.findCustomerByEmail(email).get();
		return customer;

	}
	//認証後のユーザーテスト用
//	@GetMapping("/testDebug")
//	public String testDebug() {	  
//		return "testDebug";
//	}
}
