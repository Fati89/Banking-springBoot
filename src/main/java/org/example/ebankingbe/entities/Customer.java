package org.example.ebankingbe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private long id;
    private String name;
    private String email;
    private List<BankAccount> bankAccounts;
}
