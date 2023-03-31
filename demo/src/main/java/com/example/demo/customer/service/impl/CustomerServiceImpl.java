package com.example.demo.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.customer.dto.CustomerRequestDto;
import com.example.demo.customer.dto.CustomerResponseDto;
import com.example.demo.customer.dto.CustomerUpdateDto;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<CustomerResponseDto> getAllCustomer() {
       List<Customer> customers = this.customerRepository.findAll();
       return customers.stream()
       .map(customer-> modelMapper.map(customer, CustomerResponseDto.class))
       .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getByCustomerId(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(customer, CustomerResponseDto.class);
      
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = this.customerRepository.save(modelMapper.map(customerRequestDto, Customer.class));
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerUpdateDto customerUpdateDto) {
        Customer updateCustomer = this.customerRepository.findById(customerUpdateDto.getId()).orElseThrow(EntityNotFoundException::new);
        updateCustomer.setName(customerUpdateDto.getName() ==null ? customerUpdateDto.getName():updateCustomer.getName());
        updateCustomer.setAge(customerUpdateDto.getAge() ==null ? customerUpdateDto.getAge():updateCustomer.getAge());
        Customer save = this.customerRepository.save(updateCustomer);
        return modelMapper.map(save, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto deleteCustomer(Long id) {
       Customer customer  = this.customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
       this.customerRepository.deleteById(customer.getId());
       return modelMapper.map(customer, CustomerResponseDto.class);
    }
    @Override
    public List<Customer> findCustomersWithoutOrders() {
        // TODO Auto-generated method stub
        return this.customerRepository.findCustomersWithoutOrders();
    }
    @Override
    public List<Customer> findWithOrdersByContainingWord(String word) {
        return this.customerRepository.findWithOrdersByContainingWord(word);
    }
    
}
