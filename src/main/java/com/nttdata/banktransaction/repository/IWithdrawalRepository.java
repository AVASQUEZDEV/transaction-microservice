package com.nttdata.banktransaction.repository;

import com.nttdata.banktransaction.model.Withdrawal;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository to withdrawals
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IWithdrawalRepository extends ReactiveMongoRepository<Withdrawal, String> {
}
