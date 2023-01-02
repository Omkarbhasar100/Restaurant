package com.example.Restaurant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant.entity.Admin;
import com.example.Restaurant.entity.RestaurantManager;
import com.example.Restaurant.service.AdminService;
import com.example.Restaurant.service.RestaurantManagerService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//Project based on Singleton design pattern.
//Maven is used to build the project.

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	RestaurantManagerService rms;
	@Autowired
	RestaurantManagerController rmc;
	
	//Admin can add / update / delete the restaurant
	
	@PostMapping("/admin/create")
	public Admin createAdmin (@RequestBody Admin admin) {
		return adminService.CreateRestaurant(admin);
	}
	
	@PostMapping("/admin/createRestaurant")
	public String createRestaurant(@RequestBody RestaurantManager rm) {
		return "forward:/restaurantManager/register";
	}
	
	@PutMapping("/admin/update")
	public Admin updateAdmin(@RequestBody Admin admin, @RequestParam Long id) {
		return adminService.updateRestaurant(admin, id);
	}
	
	@PutMapping("/admin/updateReataurant")
	public String updateRestaurant(@RequestBody RestaurantManager restaurantManager, @RequestParam Long r_id) {
		return "forward:/restaurantManager/update";
	}
	
	@DeleteMapping("/admin/delete")
	public String deleteAdmin(Long id) {
		return adminService.deleteRestaurantById(id);
	}
	
	@DeleteMapping("/admin/deleteRestaurant")
	public String deleteRestaurant(Long r_id) {
		return "forward:/restaurantManager/delete";
	}
	
	//Admin can approve the service provider request
	
	@GetMapping("/admin/requests")
	public ResponseEntity<?> getRequests(){
		List<RestaurantManager> rl = new ArrayList<>();
		for(RestaurantManager rm : rms.getAllRestaurant()) {
			if(!rm.isActive())
				rl.add(rm);
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "sucess");
		result.put("data", rl);
		return ResponseEntity.ok(result);
	}
	
	//Admin can also deactivate the service provider request
	
	@PutMapping("/admin/status")
	public ResponseEntity<?> restaurantStatus(@RequestParam Long id, @RequestParam Boolean status){
		RestaurantManager rm = rms.getById(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if(rm.isActive() == status) {
			result.put("status", "No need to change");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
			
		rm.setActive(status);
		result.put("status", "sucess");
		result.put("data", rms.createRestaurantManager(rm));
		return ResponseEntity.ok(result);
	}
	
	
	
}
