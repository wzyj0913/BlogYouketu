package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.BlogEntity;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface BlogDao extends JpaRepository<BlogEntity,Long>{
	
		BlogEntity save (BlogEntity blogEntity);
		
		List<BlogEntity>findAll();
		
		BlogEntity findByBlogId(Long blogId);
		
		BlogEntity findByBlogTitle(String blogTitle);
		
		int deleteByBlogId(Long blogId );
		
		

	}


