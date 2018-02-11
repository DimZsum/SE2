package net.ziemers.swxercise.lg.model.user;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import net.ziemers.swxercise.db.BaseEntity;

@Entity 
/*@NamedQueries({
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByUser", query = "SELECT p FROM Post p WHERE lower(p.user) = lower(:name)")})
*/
public class Post{
	public Post(String nachricht2 , String user) {
		this.nachricht = nachricht2;
		this.user = user;
	}
	
	public Post( ) {}
	
	public String getNachricht() {
		return nachricht;
	}
	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}


	private String nachricht;
	private Date datum;
	private String user;	
	
	
}
