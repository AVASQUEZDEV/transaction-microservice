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
 * This class defines the model of withdrawal
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "withdrawals")
public class Withdrawal {

    @Id
    private String id;

    @Field(name = "person_id", write = Field.Write.NON_NULL)
    private String clientId;

    @Field(name = "origin_account", write = Field.Write.NON_NULL)
    private String originAccount;

    @Field(name = "account_type", write = Field.Write.NON_NULL)
    private String accountType;

    @Field(name = "issue_bank", write = Field.Write.NON_NULL)
    private String issueBank;

    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @Field(name = "transaction_date")
    private Date transactionDate;

    public Withdrawal(String clientId, String originAccount, String accountType, String issueBank, Float amount, Date transactionDate) {
        this.clientId = clientId;
        this.originAccount = originAccount;
        this.accountType = accountType;
        this.issueBank = issueBank;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
}
