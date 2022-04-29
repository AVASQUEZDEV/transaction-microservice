package com.nttdata.banktransaction.dto.request;

import lombok.Data;

/**
 * This class defines the request of credit
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class CreditRequest {

    private String personId;
    private Float amount;

}
