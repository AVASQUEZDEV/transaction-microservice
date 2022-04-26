package com.nttdata.banktransaction.dto.request;

import lombok.Data;

/**
 * This class defines the request of withdrawal
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class WithdrawalRequest {

    private String originAccount;

    private String issueBank;

    private Float amount;

}
