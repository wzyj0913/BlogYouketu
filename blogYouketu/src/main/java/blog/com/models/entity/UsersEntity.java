package blog.com.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
//紐づけ処理
@Data
//引数無しのデフォルトコンストラクタを自動生成する
@NoArgsConstructor

@AllArgsConstructor
//finalなフィールドを初期化する引数付きコンストラクタを自動生成する
@RequiredArgsConstructor
//エンティティクラスであることを示す
@Entity
//テーブル名の指定をする
@Table(name="users")

public class UsersEntity {
	//主Kであることを示す
	@Id
	//主Kが自動的に作り出しを指定する
	@GeneratedValue(strategy=GenerationType.AUTO)
	//user情報のId
	private long userId;
	//値がnullにしてはいけない規制を示す
	@NonNull
	//ユーザの名前
	private String userName;
	//値がnullにしてはいけない規制を示す
	@NonNull
	//ユーザのメール
	private String email;
	//値がnullにしてはいけない規制を示す
	@NonNull
	//ユーザのパスワード
	private String password;
	

}
