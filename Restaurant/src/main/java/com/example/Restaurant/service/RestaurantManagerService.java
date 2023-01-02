package com.example.Restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dao.RestaurantManagerRepository;
import com.example.Restaurant.entity.RestaurantManager;

@Service
public class RestaurantManagerService {

	@Autowired
	RestaurantManagerRepository rms;
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	public List<RestaurantManager> getAllRestaurant(){
		return rms.findAll();
	}
	
	public RestaurantManager getById(Long id) {
		return  rms.findById(id).get();
	}
	
	public RestaurantManager getByMobile(String mobile) {
		return rms.findByMobile(mobile);
	}
	
	public RestaurantManager createRestaurantManager(RestaurantManager rm) {
		return rms.save(rm);
	}
	public RestaurantManager resisterRestaurant(RestaurantManager rm) {
		return restaurantManagerRepository.save(rm);
	}

	public RestaurantManager updateRestaurant(RestaurantManager restaurantManager, Long r_id) {
		Optional<RestaurantManager> opRM=restaurantManagerRepository.findById(r_id);
		RestaurantManager restman=opRM.get();
		restman.setResisterResto(restaurantManager.getResisterResto());
		return restaurantManagerRepository.save(restman);
	}

	public String deleteRestaurant(Long r_id) {
		restaurantManagerRepository.deleteById(r_id);
		return "Successfully deleted";
	}

	
}
