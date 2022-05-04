package com.nttdata.banktransaction.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * This class defines the request of credit
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class CreditRequest {

    private String clientId;

    private Float amount;

    private Long feesQuantity;

    private Date expirationDate;

    private String creditStatus;

}
