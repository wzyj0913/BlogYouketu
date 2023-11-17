package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.UsersService;

@Controller
public class RegisterController {
	@Autowired
	private UsersService usersService;
	//登録画面の表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return"register.html";
	}
	//登録処理
	@PostMapping("/register/process")
	public String register(@RequestParam String username,
			               @RequestParam String email,
			               @RequestParam String password) {
		if(usersService.createUsers(username, email, password)) {
			return"login.html";
		}else {
			return"register.html";
		}
	}

}
