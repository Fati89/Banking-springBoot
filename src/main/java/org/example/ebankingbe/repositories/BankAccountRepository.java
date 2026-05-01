package org.example.ebankingbe.repositories;

import org.example.ebankingbe.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
