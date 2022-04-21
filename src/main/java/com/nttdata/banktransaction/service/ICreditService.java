package com.nttdata.banktransaction.service;

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

    Mono<Credit> create(Credit creditRequest);

    Mono<Credit> update(String id, Credit creditRequest);

    Mono<Void> delete(String id);

}
