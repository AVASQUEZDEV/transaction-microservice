package com.nttdata.banktransaction.repository;

import com.nttdata.banktransaction.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines the repository to credits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface ICreditRepository extends ReactiveMongoRepository<Credit, String> {

    Mono<Credit> findByClientId(String id);


}
