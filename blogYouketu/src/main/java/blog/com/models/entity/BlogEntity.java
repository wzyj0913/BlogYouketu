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

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "blog")
public class BlogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long blogId;

	private String blogTitle;

	private String blogImage;

	private String blogContent;

	private Long userId;

	public BlogEntity(String blogTitle, String blogImage, String blogContent, Long userId) {

		this.blogTitle = blogTitle;
		this.blogImage = blogImage;
		this.blogContent = blogContent;
		this.userId = userId;
	}

	

}
