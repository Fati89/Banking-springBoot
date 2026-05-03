package org.example.ebankingbe.services;


import org.example.ebankingbe.dtos.*;
import org.example.ebankingbe.exceptions.BalanceNotSufficientException;
import org.example.ebankingbe.exceptions.BankAccountNotFoundException;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentAccountDTO saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficientException, BankAccountNotFoundException;
    List<BankAccountDTO> bankAccountList();
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long custumerId);
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
    List<BankAccountDTO> getCustomerAccounts(long id);
}
