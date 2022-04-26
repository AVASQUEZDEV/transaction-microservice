package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.dto.request.PaymentRequest;
import com.nttdata.banktransaction.model.Payment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines the service of payments
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface IPaymentService {

    Flux<Payment> findAll();

    Mono<Payment> create(PaymentRequest request);

    Mono<Payment> update(String id, PaymentRequest request);

    Mono<Void> delete(String id);

}
