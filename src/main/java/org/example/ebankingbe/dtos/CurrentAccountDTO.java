package org.example.ebankingbe.dtos;

import lombok.Data;
import org.example.ebankingbe.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentAccountDTO extends BankAccountDTO{
    private double overDraft;

}
