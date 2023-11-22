package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.UsersEntity;
import blog.com.services.UsersService;
import jakarta.servlet.http.HttpSession;

//画面遷移用のコントローラーに付与する
@Controller
public class LoginController {
	// 自動でインスタンスの紐づけを行う
	@Autowired
	// ログインの処理を実行するため呼び出す
	private UsersService usersService;
	// 自動でインスタンスの紐づけを行う
	@Autowired
	/*
	 * 同一のWebブラウザからの複数回のリクエストを、 同一のWebブラウザからのアクセスとして処理するため
	 */
	private HttpSession session;

	/* ログイン画面表示のメソッド */
	// HTTP GETリクエストに対する紐づけ
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}

	/* ログイン処理のメソッド */
	// POSTリクエストのみをURLとコントローラーのクラスまたはメソッドを紐づけることができる
	@PostMapping("/login/process")
	// email とpassword受け取り
	public String login(@RequestParam String email, @RequestParam String password) {
		// email,passwordをチェックログインできかどうかの結果を usersに格納
		UsersEntity users = usersService.checkLogin(email, password);

		if (users == null) {
			return "login.html";

		} else {
			// セッションにログイン情報を格納する
			session.setAttribute("users", users);
			return "redirect:/blog/list";
		}
	}

	/* ログアウトのメソッド */
	@GetMapping("/logout")
	public String logout() {
		// セッションの無効化
		session.invalidate();
		return "redirect:/login";

	}

}
