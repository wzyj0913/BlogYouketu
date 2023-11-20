package blog.com.controllers;

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

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;
	//blog一覧を表示
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
	//blog登録画面の表示
   @GetMapping("/blog/register")
   public String getBlogRegisterpage(Model model) {
	UsersEntity users=(UsersEntity)session.getAttribute("users");
	if(users==null) {
		return "redirect:/login";
	}else {
		model.addAttribute("usersName",users.getUserName());
		return"blog_register.html";
	}
		
}
//blog登録のメソッド
     @PostMapping("/blog/register/process")
     public String blogRegister(@RequestParam String blogTitle,
    		                    @RequestParam MultipartFile blogImage,
    		                    @RequestParam String blogContent
    		                    ) {
    	 UsersEntity users=(UsersEntity)session.getAttribute("users");
    	 
    	 if(users==null) {
    		 return"redirect:/login";
    	 }else {
    		 String fileName=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
    				 +blogImage.getOriginalFilename();
    		 try {
    		 Files.copy(blogImage.getInputStream(),Path.of("src/main/resources/static/blog-img/" + fileName));
    		 }catch (IOException e) {
 				
 				e.printStackTrace();
 			}
    		 if(blogService.createBlog(blogTitle, fileName, blogContent, users.getUserId())) {
    			 return"redirect:/blog/list";
    		 }else {
    			 return"redirect:/blog/register";
    		 }
    	 }
    	 
     }
     //商品編集画面の表示
     @GetMapping("/blog/edit/{blogId}")
     public String getBlogEditPage(@PathVariable Long blogId,Model model) {
    	 UsersEntity users=(UsersEntity)session.getAttribute("users");
    	 if(users==null) {
    		 return"redirect:/login";
    	 }else {
    		 BlogEntity blogList=blogService.getBlogPost(blogId);
    		 if(blogList==null) {
    			 return"redirect:/blog/list";
    		 }else {
    			 model.addAttribute("blog",blogList);
    		 }
    		 return "blog_edit.html";
    	 }
    	 
     }
     @PostMapping("/blog/edit/process")
     public String editProcess( @RequestParam Long blogId,
    		                    @RequestParam String blogTitle,
    		                    @RequestParam MultipartFile blogImage,
    		                    @RequestParam String blogContent) {
    	 UsersEntity users=(UsersEntity)session.getAttribute("users");
    	 if(users==null) {
    		 return"redirect:/login";
    	 }else {
    		 String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
 					+ blogImage.getOriginalFilename();
    		 try {
 				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
 			} catch (IOException e) {
 				
 				e.printStackTrace();
 			}
    		 if(blogService.editBlog(blogId, blogTitle, fileName, blogContent,users.getUserId())){
    			return "redirect:/blog/list"; 
    		 }else {
    			 return"redirect:/blog/edit"+blogId;
    		 }
    		 
    	 }
     }
     @PostMapping("/product/delete")
     public String delete(@RequestParam Long blogId) {
    	 UsersEntity users=(UsersEntity)session.getAttribute("users");
    	 if(users==null) {
    		 return"redirect:/login";
    	 }else {
    		 if(blogService.deleteblog(blogId)) {
    			 return"redirect:/blog/list";
    		 }else {
    			 return"redirect:/blog/edit"+blogId;
    		 }
    	 }
    	 
     }


	
	

}
