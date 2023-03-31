package com.example.demo.customer.service;

import java.util.List;

import com.example.demo.customer.dto.CustomerRequestDto;
import com.example.demo.customer.dto.CustomerResponseDto;
import com.example.demo.customer.dto.CustomerUpdateDto;
import com.example.demo.customer.entity.Customer;

public interface CustomerService {
     List<CustomerResponseDto> getAllCustomer();
     CustomerResponseDto getByCustomerId(Long id);
     CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);    
     CustomerResponseDto updateCustomer(CustomerUpdateDto customerUpdateDto);    
     CustomerResponseDto deleteCustomer(Long id);
     List<Customer> findCustomersWithoutOrders();
     List<Customer> findWithOrdersByContainingWord(String word);
}  
