package com.nttdata.banktransaction.dto.response.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class defines the response of bank account
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankAccountResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "cardNumber")
    private String cardNumber;

    @JsonProperty(value = "securityCode")
    private Long securityCode;

    @JsonProperty(value = "expirationDate")
    private Date expirationDate;

    @JsonProperty(value = "cci")
    private String cci;

    @JsonProperty(value = "balance")
    private Float balance;

    @JsonProperty(value = "createdAt")
    private Date createdAt;

    @JsonProperty(value = "updatedAt")
    private Date updatedAt;

}
