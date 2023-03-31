package com.example.demo.order.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.order.dto.OrderRequestDto;
import com.example.demo.order.dto.OrderResponseDto;
import com.example.demo.order.dto.OrderUpdateDto;
import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderService;




@RestController
@RequestMapping(value ="/api/v1.0/orders/")
@CrossOrigin
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value= "getAll")
    public List<OrderResponseDto >getAll() {
        return this.orderService.getByOrder();
    }

    @GetMapping(value= "getById/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        return this.orderService.getByOrderId(id);
    }
    @PostMapping(value= "createOrder")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return this.orderService.createOrder(orderRequestDto);  
    }
    @PatchMapping(value= "updateOrder")
    public OrderResponseDto updateOrder(@RequestBody OrderUpdateDto orderUpdateDto) {
        return this.orderService.updateOrder(orderUpdateDto);  
    }
    @DeleteMapping(value= "delete/{id}")
    public OrderResponseDto deleteOrder(@PathVariable Long id) {
        return this.orderService.deleteOrder(id);  
    }
    @GetMapping(value = "getAllCreateDate")
    public List<Order> getAllOrderCreateDate(@RequestParam("createDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime createDate) {
   
        return this.orderService.findByAllOrderCreateDate(createDate);
    }
}



