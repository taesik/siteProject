package com.mvc.register.vo;

public class RegisterVO {
	private String id;
	private String passwd;
	private String name;
	private String address;
	private String phone_number;
	private String email;
	public RegisterVO () {}
	public RegisterVO(String id, String passwd, String name, String address, String phone_number, String email) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
	
}
