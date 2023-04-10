package com.boardgame.mall.entity;


public class UserDTO {
	
	private int user_num;
	private String user_id;
	private String user_passwd;
	private String user_name;
	private String user_RRN;
	private String user_email;
	private String user_phone;
	private String user_class;
	private String user_address;
	private String user_addressdetail;
	
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int user_num, String user_id, String user_passwd, String user_name, String user_RRN,
			String user_email, String user_phone, String user_class) {
		super();
		this.user_num = user_num;
		this.user_id = user_id;
		this.user_passwd = user_passwd;
		this.user_name = user_name;
		this.user_RRN = user_RRN;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_class = user_class;
	}
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_passwd() {
		return user_passwd;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_RRN() {
		return user_RRN;
	}
	public void setUser_RRN(String user_RRN) {
		this.user_RRN = user_RRN;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_class() {
		return user_class;
	}
	public void setUser_class(String user_class) {
		this.user_class = user_class;
	}
	
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	
	public String getUser_addressdetail() {
		return user_addressdetail;
	}
	public void setUser_addressdetail(String user_addressdetail) {
		this.user_addressdetail = user_addressdetail;
	}
	
	public String toString() {
		String s = 
				this.user_num +" "+
		this.user_id +" "+
		this.user_passwd +" "+
		this.user_name +" "+
		this.user_RRN +" "+
		this.user_email+" "+
		this.user_phone +" "+
		this.user_class ;
		return s;
				
	}
	
}
