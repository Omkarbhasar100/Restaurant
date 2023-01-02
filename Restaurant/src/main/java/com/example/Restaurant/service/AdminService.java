package com.example.Restaurant.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dao.AdminRepository;
import com.example.Restaurant.entity.Admin;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	public Admin CreateRestaurant(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin updateRestaurant(Admin admin,Long id) {
	Optional<Admin> opAdm=adminRepository.findById(id);
	Admin adam=opAdm.get();
	adam.setName(admin.getName());
		return adminRepository.save(adam);
	}
	
	public String deleteRestaurantById(Long id) {
		adminRepository.deleteById(id);
		return "Successfully deleted";
	}
	

}
