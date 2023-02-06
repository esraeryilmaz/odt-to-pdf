package com.demo.converter.model;


public class ODT {

	private String name;
	private String password;


	public ODT(String name, String password) {
		this.name = name;
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "ODT [name=" + name + ", password=" + password + "]";
	}




}
