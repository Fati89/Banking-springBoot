package org.example.ebankingbe;

import org.example.ebankingbe.entities.*;
import org.example.ebankingbe.enums.AccountStatus;
import org.example.ebankingbe.enums.OperationType;
import org.example.ebankingbe.exceptions.BalanceNotSufficientException;
import org.example.ebankingbe.exceptions.BankAccountNotFindException;
import org.example.ebankingbe.exceptions.CustomerNotFoundException;
import org.example.ebankingbe.repositories.AccountOperationRepository;
import org.example.ebankingbe.repositories.BankAccountRepository;
import org.example.ebankingbe.repositories.CustomerRepository;
import org.example.ebankingbe.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBeApplication.class, args);
    }

    @Bean
    CommandLineRunner start (BankAccountService bankAccountService){
        return args -> {
            Stream.of("Mohammed", "Youssef", "Farid").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });

            bankAccountService.listCustomers().forEach(cust->{
                try {
                    bankAccountService.saveCurrentAccount(Math.random()*90000, 9000, cust.getId());
                    bankAccountService.saveSavingAccount(Math.random()*120000, 5.5, cust.getId());
                    List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
                    for(BankAccount acc:bankAccounts){
                        for(int i=0; i<1; i++){
                            bankAccountService.credit(acc.getId(), 10000+Math.random()*120000, "credit");
                            bankAccountService.debit(acc.getId(), 1000+Math.random()*9000, "debit");
                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }catch (BankAccountNotFindException e) {
                    throw new RuntimeException(e);
                } catch (BalanceNotSufficientException e) {
                    throw new RuntimeException(e);
                }
            });

            bankAccountService.transfer(bankAccountService.bankAccountList().get(0).getId(),bankAccountService.bankAccountList().get(2).getId(), 100);
        };
    }

    // ------------------------------------------------------------------------------------

    // @Bean
    CommandLineRunner start (CustomerRepository customerRepository, // C’est un outil Spring Boot qui s’exécute automatiquement au démarrage de l’application️ ---> donc ce code tourne une seule fois au lancement
                             BankAccountRepository bankAccountRepository, // Les paramètres (Injection automatique)
                             AccountOperationRepository accountOperationRepository) {
        return args -> {

            Stream.of("Hassan", "Yassine", "Aicha").forEach(name->{ // créer une liste des noms
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId (UUID.randomUUID().toString());
                currentAccount.setBalance (Math.random()*90000);
                currentAccount.setCreatedAt (new Date());
                currentAccount.setStatus (AccountStatus.CREATED);
                currentAccount.setCustomer (cust);
                currentAccount.setOverDraft (9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId (UUID.randomUUID().toString());
                savingAccount.setBalance (Math.random()*90000);
                savingAccount.setCreatedAt (new Date());
                savingAccount.setStatus (AccountStatus.CREATED);
                savingAccount.setCustomer (cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach( acc->{
                for(int i=0; i<5; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*120000);
                    accountOperation.setOperationType(Math.random()>0.5?OperationType.DEBIT:OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });

        };
    }
}
