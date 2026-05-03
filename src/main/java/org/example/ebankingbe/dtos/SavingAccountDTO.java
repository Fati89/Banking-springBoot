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
public class SavingAccountDTO extends BankAccountDTO{
    private double interestRate;

}
