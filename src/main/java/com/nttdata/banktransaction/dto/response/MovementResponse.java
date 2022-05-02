package com.nttdata.banktransaction.dto.response;

import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.model.Withdrawal;
import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

/**
 * This class defines the response of movements
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class MovementResponse {

    private Flux<DepositResponse> deposits;

    private Flux<WithdrawalResponse> withdrawals;

}
