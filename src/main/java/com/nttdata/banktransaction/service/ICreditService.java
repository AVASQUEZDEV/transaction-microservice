package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.dto.request.CreditRequest;
import com.nttdata.banktransaction.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines the service of credits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface ICreditService {

    Flux<Credit> findAll();

    Mono<Credit> findById(String id);

    Flux<Credit> findByClientId(String clientId);

    Mono<Credit> create(CreditRequest request);

    Mono<Credit> update(String id, CreditRequest request);

    Mono<Void> delete(String id);

}
