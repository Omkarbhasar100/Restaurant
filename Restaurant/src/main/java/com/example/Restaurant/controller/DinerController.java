package com.example.Restaurant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant.entity.Booking;
import com.example.Restaurant.entity.Diner;
import com.example.Restaurant.entity.RestaurantManager;
import com.example.Restaurant.service.BookingService;
import com.example.Restaurant.service.DinerService;
import com.example.Restaurant.service.RestaurantManagerService;

@RestController
public class DinerController {
	@Autowired
	DinerService dinerService;
	@Autowired
	RestaurantManagerService rms;
	@Autowired
	BookingService bs;
	
	//User should be able to register themselves
	
	@PostMapping("/diner")
	public Diner createDiner1(@RequestBody Diner diner) {
		return dinerService.createDiner(diner);
	}
	
	@PostMapping("/diner/create")
	public ResponseEntity<?> createDiner(@RequestBody Diner diner) {
		
		HashMap<String, Object> result = new HashMap<>();
		if(diner.getMobile().length() == 10 && diner.getEmail().contains("@")) {
			List<Diner> all = dinerService.getAllDiners();
			for(Diner d : all) {
				if(d.equals(diner)) {
					result.put("status", "Diner Already Exist");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
				}
			}
			result.put("status", "Sucess");
			result.put("Diner", dinerService.createDiner(diner));
			return ResponseEntity.ok(result); 
		}else {
			result.put("status", "Invalid mobile or email");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}	
	}
	
	
	@GetMapping("/diner/restaurant")
	public ResponseEntity<?> getActiveRestaurant(){
		List<RestaurantManager> rm = new ArrayList<>();
		for(RestaurantManager r : rms.getAllRestaurant()) {
			if(r.isActive())
				rm.add(r);
		}
		HashMap<String, Object> resul = new HashMap<String, Object>();
		resul.put("status", "sucess");
		resul.put("data", rm);
		return ResponseEntity.ok(resul);
	}
	
	//User should be able to book the table
	
	@PutMapping("/diner/book")
	public ResponseEntity<?> bookTable(@RequestParam String uMobile, @RequestParam int tables, @RequestParam Long rId ){
		Diner d = dinerService.getByMobile(uMobile);
		Booking b = new Booking(d.getD_id(), rId, tables, true);
		RestaurantManager rm = rms.getById(rId);
		rm.setTableCapacity(rm.getTableCapacity() - tables);
		rms.createRestaurantManager(rm);
		HashMap<String, Object> resul = new HashMap<String, Object>();
		resul.put("status", "sucess");
		resul.put("data", bs.createBooking(b));
		return ResponseEntity.ok(resul);
	}
	
	@PutMapping("/diner/bookings")
	public ResponseEntity<?> getBooking(@RequestParam String uMobile){
		Diner d = dinerService.getByMobile(uMobile);
		List<Booking> bl = new ArrayList<>();
		for(Booking b : bs.getAllBooking()) {
			if(d.getD_id() == b.getdId())
				bl.add(b);
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "sucess");
		result.put("data", bl);
		return ResponseEntity.ok(result);
	}
	
	//User should be able to cancel the reserved table
	
	@GetMapping("/diner/cancelBooking/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long bId){
		Booking b = bs.getById(bId);
		b.setBookingStatus(false);
		RestaurantManager rm = rms.getById(b.getrId());
		rm.setTableCapacity(rm.getTableCapacity() + b.getTables());
		rms.createRestaurantManager(rm);
		HashMap<String, Object> resul = new HashMap<String, Object>();
		resul.put("status", "sucess");
		resul.put("data", bs.createBooking(b));
		return ResponseEntity.ok(resul);
	}

}
