package com.example.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Restaurant.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
