package com.nttdata.banktransaction.dto.request.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the request of bank account
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankAccountRequest {

    private Float balance;

}
