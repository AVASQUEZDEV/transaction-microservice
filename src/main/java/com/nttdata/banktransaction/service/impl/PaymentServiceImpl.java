package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.model.Payment;
import com.nttdata.banktransaction.repository.IPaymentRepository;
import com.nttdata.banktransaction.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines the service of payments
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final IPaymentRepository paymentRepository;

    /**
     * This method returns a list of payments
     *
     * @return payments list
     */
    @Override
    public Flux<Payment> findAll() {
        return paymentRepository.findAll()
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findAll]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "" + e));
                });
    }

    /**
     * This method creates a payments
     *
     * @param paymentRequest request to create new payment
     * @return payment created
     */
    @Override
    public Mono<Payment> create(Payment paymentRequest) {
        return paymentRepository.save(paymentRequest)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
    }

    /**
     * This method updates a payment
     *
     * @param id             payment id to update
     * @param paymentRequest request to update payment
     * @return payment updated
     */
    @Override
    public Mono<Payment> update(String id, Payment paymentRequest) {
        return findAll()
                .filter(p -> p.getId().equals(id))
                .single()
                .flatMap(p -> {
                    p.setAmount(paymentRequest.getAmount());
                    return paymentRepository.save(p);
                }).onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][update]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
    }

    /**
     * This method delete a payment
     *
     * @param id payment id to delete
     * @return void
     */
    @Override
    public Mono<Void> delete(String id) {
        return paymentRepository.deleteById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][delete]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                });
    }

}
