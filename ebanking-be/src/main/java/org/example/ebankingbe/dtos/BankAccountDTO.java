package org.example.ebankingbe.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ebankingbe.entities.AccountOperation;
import org.example.ebankingbe.entities.Customer;
import org.example.ebankingbe.enums.AccountStatus;

import java.util.Date;
import java.util.List;


@Data
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customer;
    private String type;
}
