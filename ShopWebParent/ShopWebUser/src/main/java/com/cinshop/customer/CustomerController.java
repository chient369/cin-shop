package com.cinshop.customer;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
	private CustomerService service;
	
	@GetMapping("/register")
	public String registerGet(Model model) {	
		model.addAttribute("customer", new Customer());
		return "register";
	}

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
			model.addAttribute("emailSearchResult", "true");
			model.addAttribute("customer", new Customer());
			return "register";
			
		//email検索結果なし。顧客情報登録
		} else {
			//インスタンス作成
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
					System.out.println(2);
				}
				customer.setSex(sex);
			}
			
			customer.setPassword(encodePassword);
			customer.setEnable((byte)1);
			customer.setPoint(0);
			customer.setImage("img/xxx.jpg");	//あとでかえるかも
			customer.setRole("ROLE_USER");
			//顧客情報をDBに登録
			service.saveCustomer(customer);
			
			address.setCustomer(customer);
			address.setPostCode(postCode);
			address.setFirstAddress(firstAddress);
			address.setLastAddress(lastAddress);
			//アドレスをDBに登録
			service.saveAddress(address);
			
			return "redirect:/login";
		}
	}
	
	
	@GetMapping("/login")
	public String login() {	  
		return "login";
	}
	
	
	//認証後のユーザーテスト用
	@GetMapping("/testDebug")
	public String testDebug() {	  
		return "testDebug";
	}
}
