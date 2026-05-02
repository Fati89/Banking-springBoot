package org.example.ebankingbe.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.entities.Customer;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;
import org.example.ebankingbe.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {

    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long idCustomer) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(idCustomer);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{idCustomer}")
    public CustomerDTO updateCustomer(@PathVariable Long idCustomer, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(idCustomer);
        return bankAccountService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{idCustomer}")
    public void deleteCustomer(@PathVariable Long idCustomer){
        bankAccountService.deleteCustomer(idCustomer);
    }
}
