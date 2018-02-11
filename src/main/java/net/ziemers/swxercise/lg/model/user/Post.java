package net.ziemers.swxercise.lg.model.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import net.ziemers.swxercise.db.BaseEntity;

@Entity
@NamedQueries({
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id")
})

public class Post extends BaseEntity {

	private String user;
	
	private String message;
	
	public Post() {}
	
	public Post(String user, String message) {
		super();
		this.message = message;
		this.user = user;
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
