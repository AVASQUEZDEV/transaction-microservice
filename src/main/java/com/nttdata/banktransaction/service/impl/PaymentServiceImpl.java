package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.dto.mapper.PaymentMapper;
import com.nttdata.banktransaction.dto.request.PaymentRequest;
import com.nttdata.banktransaction.exceptions.CustomException;
import com.nttdata.banktransaction.model.Payment;
import com.nttdata.banktransaction.repository.IPaymentRepository;
import com.nttdata.banktransaction.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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

    private final PaymentMapper paymentMapper;

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
                    return Mono.error(CustomException.internalServerError("Internal Server Error"));
                });
    }

    /**
     * This method creates a payments
     *
     * @param request request to create new payment
     * @return payment created
     */
    @Override
    public Mono<Payment> create(PaymentRequest request) {
        return paymentMapper.toPostModel(request)
                .flatMap(paymentRepository::save)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Payment not found")));
    }

    /**
     * This method updates a payment
     *
     * @param id             payment id to update
     * @param request request to update payment
     * @return payment updated
     */
    @Override
    public Mono<Payment> update(String id, PaymentRequest request) {
        return findAll()
                .filter(p -> p.getId().equals(id))
                .single()
                .flatMap(p -> paymentMapper.toPutModel(p, request)
                        .flatMap(paymentRepository::save))
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][update]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Payment not found")));
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
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                });
    }

}
