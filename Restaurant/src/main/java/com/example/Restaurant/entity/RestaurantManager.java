package com.example.Restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RestaurantManager {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long r_id;
	String resisterResto;
	String mobile;
	String userName;
	String password;
	int tableCapacity;
	boolean isActive;
	String resName;
	String resAddress;
	
	
	
	public Long getR_id() {
		return r_id;
	}
	public void setR_id(Long r_id) {
		this.r_id = r_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public int getTableCapacity() {
		return tableCapacity;
	}
	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResAddress() {
		return resAddress;
	}
	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}
	public String getResisterResto() {
		return resisterResto;
	}
	public void setResisterResto(String resisterResto) {
		this.resisterResto = resisterResto;
	}
	
	
	
	

}
