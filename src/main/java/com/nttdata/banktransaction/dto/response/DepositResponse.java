package com.nttdata.banktransaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of deposit
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class DepositResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "destinationAccount")
    private String destinationAccount;

    @JsonProperty(value = "originAccount")
    private String originAccount;

    @JsonProperty(value = "amount")
    private Float amount;

    @JsonProperty(value = "issueBank")
    private String issueBank;

    @JsonProperty(value = "transactionDate")
    private Date transactionDate;

}
