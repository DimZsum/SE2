package net.ziemers.swxercise.lg.user.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PostDto {
	@NotNull
	private String user;
	
	@NotNull
	private String nachricht;
	
	private Date date;	
	
	public PostDto withNachricht(String nachricht) {
		this.nachricht=nachricht;
		return this;
	}
	
	public PostDto withDatum(Date date) {
		this.date=date;
		return this;
	}
	
	public PostDto withUser(String user) {
		this.user=user;
		return this;
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


}
