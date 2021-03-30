package com.security.board.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDto implements Serializable{
	
	private String userid;
	private String password;
	private String name;
	private String auth;
	private int enabled;
	
	public UserDto() {
		
	}
	
	public UserDto(String userid, String password, String name, String auth, int enabled) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.auth = auth;
		this.enabled = enabled;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", password=" + password + ", name=" + name + ", auth=" + auth + ", enabled="
				+ enabled + "]";
	}
	
}