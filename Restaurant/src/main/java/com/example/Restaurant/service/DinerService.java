package com.example.Restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dao.DinerRepository;
import com.example.Restaurant.entity.Diner;

@Service
public class DinerService {
	@Autowired
	DinerRepository dinerRepository;
	

	public Diner createDiner(Diner diner) {
		return dinerRepository.save(diner);
	}
	
	public List<Diner> getAllDiners(){
		return dinerRepository.findAll();
	}
	
	public Diner getByMobile(String mobile) {
		return dinerRepository.findByMobile(mobile);
	}

}
