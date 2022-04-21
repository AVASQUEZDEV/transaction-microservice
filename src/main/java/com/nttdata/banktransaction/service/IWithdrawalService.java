package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.model.Withdrawal;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines the service of withdrawals
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface IWithdrawalService {

    Flux<Withdrawal> findAll();

    Mono<Withdrawal> create(Withdrawal withdrawalRequest);

    Mono<Withdrawal> update(String id, Withdrawal withdrawalRequest);

    Mono<Void> delete(String id);

}
