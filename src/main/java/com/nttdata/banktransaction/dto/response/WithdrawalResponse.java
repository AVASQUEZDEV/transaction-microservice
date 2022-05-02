package com.nttdata.banktransaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of withdrawal
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class WithdrawalResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "clientId")
    private String clientId;

    @JsonProperty(value = "originAccount")
    private String originAccount;

    @JsonProperty(value = "accountType")
    private String accountType;

    @JsonProperty(value = "issueBank")
    private String issueBank;

    @JsonProperty(value = "amount")
    private Float amount;

    @JsonProperty(value = "transactionDate")
    private Date transactionDate;

}
