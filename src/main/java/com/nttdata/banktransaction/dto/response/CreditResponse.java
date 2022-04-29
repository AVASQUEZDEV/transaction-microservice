package com.nttdata.banktransaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of credit
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class CreditResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "personId")
    private String personId;

    @JsonProperty(value = "amount")
    private Float amount;

    @JsonProperty(value = "createdAt")
    private Date createdAt;

    @JsonProperty(value = "updatedAt")
    private Date updatedAt;

}
