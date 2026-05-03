package org.example.ebankingbe.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ebankingbe.entities.BankAccount;
import org.example.ebankingbe.enums.OperationType;

import java.util.Date;


@Data
public class AccountOperationDTO {
    public long id;
    public Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}
