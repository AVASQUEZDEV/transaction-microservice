package com.nttdata.banktransaction.repository;

import com.nttdata.banktransaction.model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository to payments
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IPaymentRepository extends ReactiveMongoRepository<Payment, String> {
}
