package org.example.ebankingbe.repositories;

import org.example.ebankingbe.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
