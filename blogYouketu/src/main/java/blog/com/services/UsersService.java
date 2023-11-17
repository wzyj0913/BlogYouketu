package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.UsersDao;
import blog.com.models.entity.UsersEntity;

@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;

	public boolean createUsers(String username, String email, String password) {
		if (usersDao.findByEmail(email) == null) {
			usersDao.save(new UsersEntity(username, email, password));
			return true;
		} else {
			return false;
		}
	}

	public UsersEntity checkLogin(String email, String password) {
		System.out.println("Service"+email);
		System.out.println("Service"+password);
		UsersEntity usersEntity = usersDao.findByEmailAndPassword(email, password);

		if (usersEntity == null) {
			return null;
		} else {
			return usersEntity;
		}

	}

}
