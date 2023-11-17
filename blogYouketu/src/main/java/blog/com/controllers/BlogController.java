package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.BlogEntity;
import blog.com.models.entity.UsersEntity;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		UsersEntity users=(UsersEntity)session.getAttribute("users");
		if(users==null) {
			return "redirect:/login";
		}else {
			List<BlogEntity>blogList=blogService.SelectAll(users.getUserId());
			model.addAttribute("blogList",blogList);
			model.addAttribute("userName",users.getUserName());
			return"blog_list.html";
		}
	}
	
	
	
	

}
