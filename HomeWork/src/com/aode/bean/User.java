package com.aode.bean;

public class User {
	private int id;
	private String username;
	private String password;
	private String sex;
	private String[] hobby;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public User(String username, String password, String sex, String[] hobby) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.hobby = hobby;
	}
	public User() {
		super();
	}
	public User(int id, String username, String password, String sex, String[] hobby) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.hobby = hobby;
	}
	
	
}
