package com.example.demo.order.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    
   
    List<Order> findByCreateDateAfter(LocalDateTime createDate);
}
