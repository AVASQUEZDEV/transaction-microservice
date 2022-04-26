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

    private String destinationAccount;

    private String originAccount;

    private Float amount;

    private String issueBank;

}
