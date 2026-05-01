package org.example.ebankingbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ebankingbe.enums.OperationType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public Date operationDate;
    private double amount;
    private OperationType operationType;
    @ManyToOne
    private BankAccount bankAccount;
}
