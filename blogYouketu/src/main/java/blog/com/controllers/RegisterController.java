package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.UsersService;

//画面遷移用のコントローラーに付与する
@Controller
public class RegisterController {
	// 自動でインスタンスの紐づけを行う
	@Autowired
	// 新規登録の処理を実行するため呼び出す
	private UsersService usersService;

	// 登録画面の表示
	// HTTP GETリクエストに対する紐づけ
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	// 登録処理
	@PostMapping("/register/process")
	// httpからname email password 受け取り
	public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
		// 新規登録処理login.htmlに返す
		if (usersService.createUsers(username, email, password)) {
			return "login.html";
		} else {
			// じゃなければ、register.htmlを続く
			return "register.html";
		}
	}

}
