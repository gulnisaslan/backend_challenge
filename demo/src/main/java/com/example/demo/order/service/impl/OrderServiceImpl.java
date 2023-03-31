package com.example.demo.order.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.order.dto.OrderRequestDto;
import com.example.demo.order.dto.OrderResponseDto;
import com.example.demo.order.dto.OrderUpdateDto;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;

import com.example.demo.order.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService  {

    private final  OrderRepository orderRepository;
    private final ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order save = this.orderRepository.save(mapper.map(orderRequestDto, Order.class));
       return mapper.map(save, OrderResponseDto.class);
    }



    // ToDO: update method will write
    @Override
    public OrderResponseDto updateOrder(OrderUpdateDto orderUpdateDto) {
        Order updateOrder = this.orderRepository.findById(orderUpdateDto.getId())
        .orElseThrow(EntityNotFoundException::new);
        updateOrder.setTotalPrice(orderUpdateDto.getTotalPrice() == null ? updateOrder.getTotalPrice():orderUpdateDto.getTotalPrice());
        Order save = this.orderRepository.save(updateOrder);
        return mapper.map(save, OrderResponseDto.class);
        
    }

    @Override
    public OrderResponseDto deleteOrder(Long id) {
        Order deleteOrder = this.orderRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
         this.orderRepository.delete(deleteOrder);
        return mapper.map(deleteOrder, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto getByOrderId(Long id) {
        Order getByOrderId = this.orderRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
        return mapper.map(getByOrderId, OrderResponseDto.class);
    }

    @Override
    public List<OrderResponseDto> getByOrder() {
          List<Order> findAll = this.orderRepository.findAll();
          
          return findAll.stream()
          .map(order ->mapper.map(order, OrderResponseDto.class))
          .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByAllOrderCreateDate(LocalDateTime createDate) {
      return this.orderRepository.findByCreateDateAfter(createDate);
    }  
}


