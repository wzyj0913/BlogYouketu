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
@RequiredArgsConstructor
@Entity
@Table(name="users")

public class UsersEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long userId;
	@NonNull
	private String userName;
	@NonNull
	private String email;
	@NonNull
	private String password;
	

}
