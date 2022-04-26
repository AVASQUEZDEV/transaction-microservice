package com.nttdata.banktransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * This class defines the model of deposits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deposits")
public class Deposit {

    @Id
    private String id;

    @Field(name = "destination_account", write = Field.Write.NON_NULL)
    private String destinationAccount;

    @Field(name = "origin_account", write = Field.Write.NON_NULL)
    private String originAccount;

    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @Field(name = "issue_bank", write = Field.Write.NON_NULL)
    private String issueBank;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Field(name = "transaction_date")
    private Date transactionDate;

    public Deposit(String destinationAccount, String originAccount, Float amount, String issueBank, Date transactionDate) {
        this.destinationAccount = destinationAccount;
        this.originAccount = originAccount;
        this.amount = amount;
        this.issueBank = issueBank;
        this.transactionDate = transactionDate;
    }
}
