package com.example.Restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Diner {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	Long d_id;
	
	String name;
	
	String mobile;
	
	String email;
	
	String address;
	String userName;
	String password;
	
	public Long getD_id() {
		return d_id;
	}

	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		Diner other = (Diner) obj;
		return this.getMobile().equals(other);
	}


}
