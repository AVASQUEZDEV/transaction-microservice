package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.model.Deposit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines the service of deposits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface IDepositService {

    Flux<Deposit> findAll();

    Mono<Deposit> create(Deposit depositRequest);

    Mono<Deposit> update(String id, Deposit depositRequest);

    Mono<Void> delete(String id);

}
