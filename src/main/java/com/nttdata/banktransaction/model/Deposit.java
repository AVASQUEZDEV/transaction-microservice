package com.nttdata.banktransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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

}
