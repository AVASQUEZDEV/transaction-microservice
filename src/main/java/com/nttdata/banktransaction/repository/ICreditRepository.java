package com.nttdata.banktransaction.repository;

import com.nttdata.banktransaction.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository to credits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface ICreditRepository extends ReactiveMongoRepository<Credit, String> {
}
