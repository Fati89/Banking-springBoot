package org.example.ebankingbe.mappers;

import org.example.ebankingbe.dtos.AccountOperationDTO;
import org.example.ebankingbe.dtos.CurrentAccountDTO;
import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.dtos.SavingAccountDTO;
import org.example.ebankingbe.entities.AccountOperation;
import org.example.ebankingbe.entities.CurrentAccount;
import org.example.ebankingbe.entities.Customer;
import org.example.ebankingbe.entities.SavingAccount;
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
    public SavingAccountDTO fromSavingAccount (SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO=new SavingAccountDTO();
        BeanUtils.copyProperties (savingAccount, savingAccountDTO);
        savingAccountDTO.setCustomer (fromCustomer (savingAccount.getCustomer()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }
    public SavingAccount fromSavingAccountDTO (SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties (savingAccountDTO, savingAccount);
        savingAccount.setCustomer (fromCustomerDTO (savingAccountDTO.getCustomer()));
        return savingAccount;
    }
    public CurrentAccountDTO fromCurrentAccount (CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO=new CurrentAccountDTO();
        BeanUtils.copyProperties (currentAccount, currentAccountDTO);
        currentAccountDTO.setCustomer (fromCustomer (currentAccount.getCustomer()));
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentAccountDTO;
    }
    public CurrentAccount fromCurrentAccountDTO (CurrentAccountDTO currentAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties (currentAccountDTO, currentAccount);
        currentAccount.setCustomer (fromCustomerDTO (currentAccountDTO.getCustomer()));
        return currentAccount;
    }
    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO){

        AccountOperation accountOperation = new AccountOperation();
        BeanUtils.copyProperties(accountOperationDTO, accountOperation);
        return accountOperation;
    }
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){

        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}
