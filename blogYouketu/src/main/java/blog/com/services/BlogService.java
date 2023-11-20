package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.BlogEntity;


@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	//blog一覧部分
	public List<BlogEntity>SelectAll(Long userId){
		if(userId==null) {
			return null;
		}else {
			return blogDao.findAll();
		}
		
	}
	
	//bolg登録
	//新しいblogを作る
	public boolean createBlog(String blogTitle,String blogImage,String blogContent,Long userId) {
		//title名は存在していない場合は新しいblogを保存
		if(blogDao.findByBlogTitle(blogTitle)==null) {
			blogDao.save(new BlogEntity(blogTitle,blogImage,blogContent,userId));
			return true;
		}else {
			return false;
		}
	}
	//編集する情報を表示
	public BlogEntity getBlogPost(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	// 編集したい内容を保存する
	public boolean editBlog(Long blogId,String blogTitle,String blogImage,String blogContent,Long userId) {
	   if(blogId==null) {
		   return false;
	   }else {
		   blogDao.save(new BlogEntity(blogId,blogTitle,blogImage,blogContent,userId));
		   return true;
		   
	   }
	}
	//削除する
	public boolean deleteblog(Long blogId) {
		if(blogId==null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

}
