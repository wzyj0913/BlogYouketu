package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.BlogEntity;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface BlogDao extends JpaRepository<BlogEntity,Long>{
	
	// 引数で渡されたBlogEntityオブジェクトをDBに格納する	
		BlogEntity save (BlogEntity blogEntity);
		//BlogEntityListの内容全部検索結果を出す
		List<BlogEntity>findAll();
		//blogIdを検索
		BlogEntity findByBlogId(Long blogId);
		//blogTitleを検索
		BlogEntity findByBlogTitle(String blogTitle);
		//blogIdが一致してるものを削除
		int deleteByBlogId(Long blogId );
		//blogキーワードを検索
		List<BlogEntity>findByBlogTitleOrBlogContent(String blogTitle,String BlogContent);
		
	
		
		

	}


