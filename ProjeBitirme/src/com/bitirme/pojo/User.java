package com.bitirme.pojo;

public class User {

	private String username;
	private String password;
	private String email;
	private String tel;
	private String yetki;
	private String ilgialani;
	
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
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getYetki() {
		return yetki;
	}
	public void setYetki(String yetki) {
		this.yetki = yetki;
	}	
	public String getIlgialani() {
		return ilgialani;
	}
	public void setIlgialani(String ilgialani) {
		this.ilgialani = ilgialani;
	}
	public User() {}
		
	public User(String username,String password,String email,String tel,String yetki,String ilgialani) {
		this.username=username;
		this.password=password;
		this.email=email;
		this.tel=tel;
		this.yetki=yetki;
		this.ilgialani=ilgialani;
	}
	
}
