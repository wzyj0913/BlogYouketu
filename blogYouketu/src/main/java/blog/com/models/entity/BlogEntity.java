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
//全ての引数を持つコンストラクタを自動生成する
@AllArgsConstructor
//エンティティクラスであることを示す
@Entity
//テーブル名の指定をする
@Table(name = "blog")
public class BlogEntity {
	//主Kであることを示す
	@Id
	//主Kが自動的に作り出しを指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	 //blogのID
	private long blogId;

	private String blogTitle;

	private String blogImage;

	private String blogContent;

	private Long userId;
    //新規bolgを格納するため
	public BlogEntity(String blogTitle, String blogImage, String blogContent, Long userId) {

		this.blogTitle = blogTitle;
		this.blogImage = blogImage;
		this.blogContent = blogContent;
		this.userId = userId;
	}

	

}
