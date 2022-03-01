package com.nttdata.bcp1.msaccout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("account")
public class Account {
    @Id
    private String id;
    private String idCustomer;
    private Float balance;
    private Float maintenace;
    private Integer numberTransaction;
    private Integer maxTransaction;
    private AccountType accountType;
}
