package com.example.Restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dao.BookingRepository;
import com.example.Restaurant.entity.Booking;


@Service
public class BookingService {

	@Autowired
	public BookingRepository br;
	
	public Booking createBooking(Booking b) {
		return br.save(b);
	}
	
	public Booking getById(Long id) {
		Optional<Booking> op =  br.findById(id);
		return op.get();
	}
	
	public List<Booking> getAllBooking(){
		return br.findAll();
	}
	
}
