package com.cinshop.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	@PostMapping(path = "/create")
	public String registerPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Customer customer) throws ServletException, IOException{
		
		//Customerクラスにないフィールドの値を取得
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		String[] gender = request.getParameterValues("gender");
		String[] postCode = request.getParameterValues("postCode");
		String[] firstAddress = request.getParameterValues("firstAddress");
		String[] lastAddress = request.getParameterValues("lastAddress");
		
		//インスタンス作成
		Sex sex = new Sex();
		Address address = new Address();
		
		//性別をセットする
		if (gender[0].equals("1")) {
			sex.setSex_id(1);
			sex.setSexName("男");
		} else if (gender[0].equals("2")){
			sex.setSex_id(2);
			sex.setSexName("女");
			System.out.println(2);
		}
		
		//パスワードハッシュ化
		String encodePassword = passwordEncoder.encode(customer.getPassword());
		
		//顧客の不足分の情報をセット
		customer.setPassword(encodePassword);
		customer.setSex(sex);
		customer.setEnable((byte)1);
		customer.setImage("img/xxx.jpg");	//あとでかえるかも
		customer.setRole("ROLE_USER");
		Customer savedCust = service.saveCustomer(customer);
		
		//アドレスをセットする
		address.setCustomer(customer);
		address.setPostCode(postCode[0]);
		address.setFirstAddress(firstAddress[0]);
		address.setLastAddress(lastAddress[0]);
		Address savedAddr = service.saveAddress(address);
		
		return "login";
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
