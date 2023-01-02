package com.example.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.RestaurantManager;

@Repository
public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long>{
	public RestaurantManager findByMobile(String mobile);
}
