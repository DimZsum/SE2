package net.ziemers.swxercise.lg.user.dto;

import javax.validation.constraints.NotNull;

public class PostDto {
	
	private String user;
	private String message;
	
	
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
