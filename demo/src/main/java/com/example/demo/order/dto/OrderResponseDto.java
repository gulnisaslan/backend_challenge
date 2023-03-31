package com.example.demo.order.dto;

import java.time.LocalDateTime;

import com.example.demo.customer.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Double totalPrice;
    private LocalDateTime createDate ;
    private Long customerId;
}
