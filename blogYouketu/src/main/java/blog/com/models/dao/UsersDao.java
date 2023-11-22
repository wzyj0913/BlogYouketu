package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.UsersEntity;
//DAOであることを示す
@Repository
public interface UsersDao extends JpaRepository<UsersEntity,Long> {
	// UserEntityを引数として受け取り、UserEntityを保存、保存したUserEntityを返す
	UsersEntity save(UsersEntity usersEntity);
	//emailを受け取り、UsersEntityの中に一致するものを探す
	UsersEntity findByEmail(String email);
	//emailとpasswordをを受け取りUsersEntityの中に一致するものを探す
	UsersEntity findByEmailAndPassword(String email,String password);
	
	
	

}
