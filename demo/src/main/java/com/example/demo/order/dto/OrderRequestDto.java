package com.example.demo.order.dto;

import com.example.demo.customer.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
   
    private Double totalPrice;
    private Long customerId;
}
