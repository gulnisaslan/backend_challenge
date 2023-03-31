package com.example.demo.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.customer.entity.Customer;
import com.example.demo.order.dto.OrderResponseDto;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders o WHERE o.id IS NULL")
    List<Customer> findCustomersWithoutOrders();
    
    // @Query("select new com.example.demo.order.dto.OrderResponseDto(c.id,c.name,c.age,o.id) from Order o"
    //        +" left join Customer c on o.customer.id = c.id"+ 
    //        "where c.name LIKE %:word%")
@Query(value = "Select c.id, c.name, c.age,o.id from " +
           "Order o left join Customer c on o.customer.id = c.id " +
           "WHERE c.name LIKE %:word%")
    List<Customer> findWithOrdersByContainingWord(String word);

}
