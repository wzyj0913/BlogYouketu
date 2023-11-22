package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.UsersDao;
import blog.com.models.entity.UsersEntity;

//serviceであること示す
@Service
public class UsersService {
	// 自動でインスタンスの紐づけを行う
	@Autowired
	// UsersDaoを使うから、userDaoで宣言
	private UsersDao usersDao;

	/* Userアカウントを作るメソッド */
	public boolean createUsers(String name, String email, String password) {
		// 受け取だemailをentityで検索,結果はnullの場合
		if (usersDao.findByEmail(email) == null) {
			// 新しいUsersEntityとして name,email,passwordを保存
			usersDao.save(new UsersEntity(name, email, password));
			return true;
		} else {
			return false;
		}
	}

	/* ログインをするメソッド */
	public UsersEntity checkLogin(String email, String password) {
		// 指定されたメール名とパスワードが一致するかを検索してuserEntityに格納する
		UsersEntity usersEntity = usersDao.findByEmailAndPassword(email, password);
		// 結果はnullの場合はnullを返す
		if (usersEntity == null) {
			return null;
		} else {
			// nullじゃない場合はusersEntityを返す;
			return usersEntity;
		}

	}

}
