package com.example.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.Diner;

@Repository
public interface DinerRepository extends JpaRepository<Diner, Long> {
 public Diner findByMobile(String mobile);
}
