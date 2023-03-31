package com.example.demo.order.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.order.dto.OrderRequestDto;
import com.example.demo.order.dto.OrderResponseDto;
import com.example.demo.order.dto.OrderUpdateDto;
import com.example.demo.order.entity.Order;



public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto updateOrder(OrderUpdateDto orderRequestDto);
    OrderResponseDto deleteOrder(Long id);
    OrderResponseDto getByOrderId(Long id);
    List<OrderResponseDto> getByOrder();
    List<Order> findByAllOrderCreateDate(LocalDateTime createDate);
}
