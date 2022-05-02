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

    @Field(name = "clientId", write = Field.Write.NON_NULL)
    private String clientId;

    @Field(name = "destination_account", write = Field.Write.NON_NULL)
    private String destinationAccount;

    @Field(name = "account_type", write = Field.Write.NON_NULL)
    private String accountType;

    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @Field(name = "issue_bank", write = Field.Write.NON_NULL)
    private String issueBank;

    @Field(name = "origin_account", write = Field.Write.NON_NULL)
    private String originAccount;

    @Field(name = "transaction_date")
    private Date transactionDate;

    public Deposit(String clientId, String destinationAccount, String accountType, Float amount, String issueBank, String originAccount, Date transactionDate) {
        this.clientId = clientId;
        this.destinationAccount = destinationAccount;
        this.accountType = accountType;
        this.amount = amount;
        this.issueBank = issueBank;
        this.originAccount = originAccount;
        this.transactionDate = transactionDate;
    }
}
