package org.example.ebankingbe.services;


import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.entities.BankAccount;
import org.example.ebankingbe.entities.CurrentAccount;
import org.example.ebankingbe.entities.Customer;
import org.example.ebankingbe.entities.SavingAccount;
import org.example.ebankingbe.exceptions.BalanceNotSufficientException;
import org.example.ebankingbe.exceptions.BankAccountNotFindException;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFindException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFindException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFindException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficientException, BankAccountNotFindException;

    List<BankAccount> bankAccountList();
}
