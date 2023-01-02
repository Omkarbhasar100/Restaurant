package com.example.Restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {

	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	
	Long dId;
	Long rId;
	
	int tables;
	boolean bookingStatus;

	public Booking(Long dId, Long rId, int tables, boolean bookingStatus) {
		super();
		this.dId = dId;
		this.rId = rId;
		this.tables = tables;
		this.bookingStatus = bookingStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getdId() {
		return dId;
	}

	public void setdId(Long dId) {
		this.dId = dId;
	}

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public int getTables() {
		return tables;
	}

	public void setTables(int tables) {
		this.tables = tables;
	}

	public boolean getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
}
