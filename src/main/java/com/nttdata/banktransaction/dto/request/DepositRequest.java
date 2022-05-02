package com.nttdata.banktransaction.dto.request;

import lombok.Data;

/**
 * This class defines the request of deposit
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class DepositRequest {

    private String clientId;

    private String destinationAccount;

    private String accountType;

    private Float amount;

    private String issueBank;

    private String originAccount;
    
}
