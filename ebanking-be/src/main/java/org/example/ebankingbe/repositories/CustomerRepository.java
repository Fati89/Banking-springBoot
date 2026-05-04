package org.example.ebankingbe.repositories;

import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // List<CustomerService> getCustomersByNameContains(String keyword);
    @Query("select c from Customer c where c.name like :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);
}


