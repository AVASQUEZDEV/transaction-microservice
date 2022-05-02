package com.nttdata.banktransaction.repository;

import com.nttdata.banktransaction.model.Deposit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * This interface defines the repository to deposits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IDepositRepository extends ReactiveMongoRepository<Deposit, String> {

    Mono<Deposit> findByDestinationAccount(String destinationAccount);

}
