package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.UsersEntity;

@Repository
public interface UsersDao extends JpaRepository<UsersEntity,Long> {
	//保存処理　save
	UsersEntity save(UsersEntity usersEntity);
	
	UsersEntity findByEmail(String email);
	
	UsersEntity findByEmailAndPassword(String email,String password);
	
	
	

}
