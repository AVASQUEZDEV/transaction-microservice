package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.dto.request.DepositRequest;
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

    Mono<Deposit> create(DepositRequest request);

    Mono<Deposit> update(String id, DepositRequest request);

    Mono<Void> delete(String id);

}
