package com.example.Restaurant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant.entity.Booking;
import com.example.Restaurant.entity.RestaurantManager;
import com.example.Restaurant.service.BookingService;
import com.example.Restaurant.service.RestaurantManagerService;

@RestController
public class RestaurantManagerController {
	@Autowired
	RestaurantManagerService restaurantManagerService;
	@Autowired
	BookingService bs;
	
	//Service Provider can register themselves which needs approval
	//from the admin’s end
	
	@PostMapping("/restaurantManager/register")
	public String resisterRestaurant(@RequestBody RestaurantManager restaurantManager) {
		restaurantManager.setActive(false);
		RestaurantManager rm = restaurantManagerService.resisterRestaurant(restaurantManager);
		if(rm == null)
			return "Status : Registration failed";
		return "Status : Successfully registered";
	}
	
	@PutMapping("/restaurantManager/update")
	public String updateRestaurant(@RequestBody RestaurantManager restaurantManager, @RequestParam Long r_id) {
	RestaurantManager rm = restaurantManagerService.updateRestaurant(restaurantManager, r_id);
	if(rm == null)
		return "Status : Update failed";
	return "Status : successfully updated";

	}
	@DeleteMapping("/restaurantManager/delete")
	public String deleteRestaurant(Long r_id) {
		return restaurantManagerService.deleteRestaurant(r_id);
	}
	
	//Only active service providers can provide services
	
	@PutMapping("/restaurant/bookings")
	public ResponseEntity<?> getBookings(@RequestParam String uMobile){
		RestaurantManager rm = restaurantManagerService.getByMobile(uMobile);
		List<Booking> bl = new ArrayList<>();
		for(Booking b : bs.getAllBooking()) {
			if(rm.getR_id() == b.getrId())
				bl.add(b);
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "sucess");
		result.put("data", bl);
		return ResponseEntity.ok(result);
	}
	
	//The restaurant Manager can decline the booking if tables are
	//booked
	
	@PutMapping("/restaurant/cancel/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long id){
		Booking b = bs.getById(id);
		b.setBookingStatus(false);
		b = bs.createBooking(b);
		RestaurantManager rm = restaurantManagerService.getById(b.getrId());
		rm.setTableCapacity(rm.getTableCapacity() + b.getTables());
		restaurantManagerService.createRestaurantManager(rm);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "sucess");
		result.put("data", b);
		return ResponseEntity.ok(result);
	}
}
