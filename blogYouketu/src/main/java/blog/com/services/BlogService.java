package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.BlogEntity;

//カプセル化された状態でモデル内に単独で存在するインターフェースとして提供される操作として最初に定義されたことを示す
@Service
public class BlogService {
	// 自動でインスタンスの紐づけを行う
	@Autowired
	// blogテーブルにアクセスして操作するため
	private BlogDao blogDao;

	/* blog一覧部分 */
	public List<BlogEntity> SelectAll(Long userId) {
		// blogidはnullの場合はnullを返して
		if (userId == null) {
			return null;
		} else {
			// じゃなければ探したlist内の内容を全て結果としてを返す
			return blogDao.findAll();
		}

	}

	// bolg登録
	// 新しいblogを作る
	public boolean createBlog(String blogTitle, String blogImage, String blogContent, Long userId) {
		// title名は存在していない場合は新しいblogを保存 trueを返す
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new BlogEntity(blogTitle, blogImage, blogContent, userId));
			return true;
		} else {
			return false;
		}
	}

	// 編集する情報を表示
	public BlogEntity getBlogPost(Long blogId) {
		// blogidはnullの場合はnullを返して
		if (blogId == null) {
			return null;
			// じゃなければblogId検索して、結果を返す
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 編集したい内容を保存する
	public boolean editBlog(Long blogId, String blogTitle, String blogImage, String blogContent, Long userId) {
		// blogidはnullの場合はfalseを返して
		if (blogId == null) {
			return false;
		} else {
			// じゃなければ受け取たid,title,image.content,userIdを新しいBlogEntityとして格納するtrueを返す
			blogDao.save(new BlogEntity(blogId, blogTitle, blogImage, blogContent, userId));
			return true;

		}
	}

	// 削除する
	public boolean deleteBlog(Long blogId) {
		// blogidはnullの場合はfalseを返して
		if (blogId == null) {
			return false;
		} else {
			// 一致するblogIdを削除 trueを返す
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
  public List<BlogEntity>searchBlogs(String searchKeywords){
	  return blogDao.findByBlogTitleOrBlogContent(searchKeywords, searchKeywords);
  }

}
