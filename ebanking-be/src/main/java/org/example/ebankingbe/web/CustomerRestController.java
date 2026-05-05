package org.example.ebankingbe.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ebankingbe.dtos.BankAccountDTO;
import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.exceptions.BankAccountNotFoundException;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;
import org.example.ebankingbe.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {

    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long idCustomer) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(idCustomer);
    }

    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{idCustomer}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO updateCustomer(@PathVariable Long idCustomer, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(idCustomer);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword ){
        return bankAccountService.searchCustomer(keyword);
    }

    @DeleteMapping("/customers/{idCustomer}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long idCustomer){
        bankAccountService.deleteCustomer(idCustomer);
    }
}
