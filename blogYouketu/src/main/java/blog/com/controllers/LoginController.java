package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.UsersEntity;
import blog.com.services.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private HttpSession session;
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	
	@PostMapping("/login/process")
	public String login(@RequestParam String email,@RequestParam String password) {
		System.out.println(email);
		System.out.println(password);
		UsersEntity users=usersService.checkLogin(email,password);
		
		if(users==null) {
			return"login.html";
			
		}else {
			session.setAttribute("users", users);
			return"redirect:/blog/list";
		}
	}
	
		@GetMapping("/logout")
		public String logout() {
			//セッションの無効化
			session.invalidate();
			return"redirect:/login";
			
		}
	

}
