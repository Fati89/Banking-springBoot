package org.example.ebankingbe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ebankingbe.enums.OperationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    public long id;
    public Data operationDate;
    private double amount;
    private OperationType operationType;
    private BankAccount bankAccount;
}
