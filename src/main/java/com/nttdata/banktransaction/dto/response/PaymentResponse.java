package com.nttdata.banktransaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of payment
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class PaymentResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "amount")
    private Float amount;

    @JsonProperty(value = "paymentDate")
    private Date paymentDate;

}
