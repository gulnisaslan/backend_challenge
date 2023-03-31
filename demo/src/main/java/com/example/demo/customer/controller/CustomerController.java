package com.example.demo.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customer.dto.CustomerRequestDto;
import com.example.demo.customer.dto.CustomerResponseDto;
import com.example.demo.customer.dto.CustomerUpdateDto;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.CustomerService;



@RestController
@RequestMapping(value ="/api/v1.0/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(value ="getAllCustomer")
    public  List<CustomerResponseDto> getAllCustomerResponseDto() {
        return this.customerService.getAllCustomer();
        
    }
    @GetMapping(value="getCustomer/{id}")
    public  CustomerResponseDto getCustomerResponseDto(@PathVariable Long id) {
        return this.customerService.getByCustomerId(id);
        
    }
    @PostMapping(value ="createCustomer")
    public  CustomerResponseDto createResponseDto(@RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.createCustomer(customerRequestDto);
        
    }
    @PatchMapping(value ="updateCustomer")
    public  CustomerResponseDto updateResponseDto(@RequestBody CustomerUpdateDto customerUpdateDto) {
        return this.customerService.updateCustomer(customerUpdateDto);
        
    }
    @DeleteMapping(value = "deleteCustomer/{id}")
    public  CustomerResponseDto deleteCustomer(@PathVariable("id") Long id) {
        return this.customerService.deleteCustomer(id);
        
    }
    @GetMapping(value = "findCustomersWithoutOrders")
   public  List<Customer> findCustomersWithoutOrders(){
         return this.customerService.findCustomersWithoutOrders();
    }
    @GetMapping(value ="findWithOrdersByContainingWord/{word}" )
    public List<Customer> findWithOrdersByContainingWord(@PathVariable String word) {
        return this.customerService.findWithOrdersByContainingWord(word);
    }

    
}
/*Select cs.brand_name,cs.model_year,cs.price ,ag.name as auto_gallery from cars as cs
inner join auto_gallery ag on cs.auto_gallery = ag.id 
group by count(cs) 
where cs.brand_name = 'Ford' And cs.model_year = 2023  */