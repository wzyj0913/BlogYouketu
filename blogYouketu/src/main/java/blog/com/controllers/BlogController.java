package blog.com.controllers;

//インポート
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.BlogEntity;
import blog.com.models.entity.UsersEntity;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//画面遷移用のコントローラーに付与する
@Controller
public class BlogController {
	// 自動でインスタンスの紐づけを行う
	@Autowired
	// ブログの処理を実行するため呼び出す
	private BlogService blogService;
	// 自動でインスタンスの紐づけを行う
	@Autowired
	/* sessionの管理をする*/
	private HttpSession session;

	/* blog一覧を表示のメソッド */
	// HTTP GETリクエストに対する紐づけ
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			// 現在ログインしているユーザーに関連するブログを取得している
			List<BlogEntity> blogList = blogService.SelectAll(users.getUserId());
			// コントローラーからビューに渡すためのデータを格納している
			model.addAttribute("blogList", blogList);
			model.addAttribute("userName", users.getUserName());
			return "blog_list.html";
		}
	}

	/* blog登録画面表示メソッド */
	// HTTP GETリクエストに対する紐づけ
	@GetMapping("/blog/register")
	public String getBlogRegisterpage(Model model) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			// コントローラーからビューに渡すためのデータを格納している
			model.addAttribute("userName", users.getUserName());
			return "blog_register.html";
		}

	}

	/* blog登録のメソッド */
	@PostMapping("/blog/register/process")
	public String blogRegister(@RequestParam String blogTitle, @RequestParam MultipartFile blogImage,
			@RequestParam String blogContent) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");

		if (users == null) {
			return "redirect:/login";
		} else {
			// 写真名を時間付けでsrc/main/resources/static/blog-img/を保存する
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {

				e.printStackTrace();
			} // 入力されたデータをデータベースに保存する
			if (blogService.createBlog(blogTitle, fileName, blogContent, users.getUserId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/register";
			}
		}

	}

	/* blog編集画面の表示 */
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			// blogIdに対応するブログを取得し、blogListに代入する
			BlogEntity blogList = blogService.getBlogPost(blogId);
			if (blogList == null) {
				return "redirect:/blog/list";
			} else {
				// コントローラーからビューに渡すためのデータを格納している
				model.addAttribute("blog", blogList);
				model.addAttribute("userName", users.getUserName());
			}
			return "blog_edit.html";
		}

	}

	/* 編集処理のメソッド */
	@PostMapping("/blog/edit/process")
	public String editProcess(@RequestParam Long blogId, @RequestParam String blogTitle,
			@RequestParam MultipartFile blogImage, @RequestParam String blogContent) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			// 写真名を時間付けでsrc/main/resources/static/blog-img/を保存する
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {

				e.printStackTrace();
			} // 受け取たid,title,image.content,userIdを格納する
			if (blogService.editBlog(blogId, blogTitle, fileName, blogContent, users.getUserId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit/" + blogId;
			}

		}
	}

	/* Blog削除するメソッド */
	@PostMapping("/blog/delete")
	public String delete(@RequestParam Long blogId) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			if (blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit/" + blogId;
			}
		}

	}

	// mypageのメソッド
	@GetMapping("/mypage")
	public String getMypage(Model model) {
		// セッションから現在のユーザー情報を取得しusersで格納する、nullの場合はlogin画面に戻す
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		if (users == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("userName", users.getUserName());
			return "mypage.html";
		}

	}
	@PostMapping("/search")
	public String blogSearch(@RequestParam String searchKeywords,  Model model) {
		UsersEntity users = (UsersEntity) session.getAttribute("users");
		
		// ブログの検索を行い、リストに格納
		List<BlogEntity> blogList = blogService.searchBlogs(searchKeywords);
		model.addAttribute("blogList" , blogList);
		model.addAttribute("userName", users.getUserName());
		return "search.html";
	}

}
