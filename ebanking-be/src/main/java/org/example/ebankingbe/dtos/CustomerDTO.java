package org.example.ebankingbe.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CustomerDTO {

    private long id;
    private String name;
    private String email;
}
