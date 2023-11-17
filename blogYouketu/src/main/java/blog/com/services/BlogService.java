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
	
	public List<BlogEntity>SelectAll(Long userId){
		if(userId==null) {
			return null;
		}else {
			return blogDao.findAll();
		}
		
	}
	

}
