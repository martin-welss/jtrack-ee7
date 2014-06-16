package itf.jtrack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userid;
	
	@NotNull
	@Column(unique=true)
	@Size(min=2, max=30)
	private String name;

	private String email;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email
				+ "]";
	}

}
