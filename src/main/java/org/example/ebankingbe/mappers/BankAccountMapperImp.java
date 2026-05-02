package org.example.ebankingbe.mappers;

import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

// MapStruct
@Service
public class BankAccountMapperImp {

    public CustomerDTO fromCustomer (Customer customer) {
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties (customer, customerDTO);
        // customerDTO.setId(customer.getId());
        // customerDTO.setName (customer.getName());
        // customerDTO.setEmail (customer.getEmail());
        return customerDTO;
    }
    public Customer fromCustomerDTO (CustomerDTO customerDTO) {
        Customer customer=new Customer();
        BeanUtils.copyProperties (customerDTO, customer);
        return customer;
    }
}
