package org.example.ebankingbe.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ebankingbe.dtos.CustomerDTO;
import org.example.ebankingbe.entities.*;
import org.example.ebankingbe.enums.OperationType;
import org.example.ebankingbe.exceptions.BalanceNotSufficientException;
import org.example.ebankingbe.exceptions.BankAccountNotFindException;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;
import org.example.ebankingbe.mappers.BankAccountMapperImp;
import org.example.ebankingbe.repositories.AccountOperationRepository;
import org.example.ebankingbe.repositories.BankAccountRepository;
import org.example.ebankingbe.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImp implements BankAccountService{

    // Logger log = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImp dtoMapper;


    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("saving new customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        log.info("saving a current account");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("customer not found");
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        CurrentAccount savedCurrentAccount = bankAccountRepository.save(currentAccount);
        return savedCurrentAccount;
    }

    @Override
    public SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        log.info("saving a saving account");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("customer not found");
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        SavingAccount savedSavingAccount = bankAccountRepository.save(savingAccount);
        return savedSavingAccount;
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        log.info("Checking customers list");
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(cust->dtoMapper.fromCustomer(cust))
                .collect(Collectors.toList());
        /*
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer: customers) {
            CustomerDTO customerDTO=dtoMapper.fromCustomer (customer);
            customerDTOS.add(customerDTO);
        }
        */
        return customerDTOS;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFindException {
        log.info("Checking bank account");
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFindException("Bank account not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFindException, BalanceNotSufficientException {
        log.info("Debit: "+amount);
        BankAccount bankAccount = getBankAccount(accountId);
        if(bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Bulence not sufficient");

        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount (amount);
        accountOperation.setDescription (description);
        accountOperation.setOperationDate (new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFindException {
        log.info("Credit: "+amount);
        BankAccount bankAccount = getBankAccount(accountId);
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount (amount);
        accountOperation.setDescription (description);
        accountOperation.setOperationDate (new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficientException, BankAccountNotFindException {
        debit(accountIdSource, amount, "transfer to "+accountIdDestination);
        credit(accountIdDestination, amount, "transfer from "+accountIdSource);
    }

    @Override
    public List<BankAccount> bankAccountList(){
        return bankAccountRepository.findAll();
    }
}
