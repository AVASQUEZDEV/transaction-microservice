package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.model.Payment;
import com.nttdata.banktransaction.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * This controller class defines the endpoints to payments
 *
 * @author Alcibar Vasquesz
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentRestController {

    private final IPaymentService paymentService;

    /**
     * @return list of payments
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Payment> getAll() {
        return paymentService.findAll();
    }

    /**
     * @param payment request to create payment
     * @return payment created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Payment> create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    /**
     * @param id             payment id to update
     * @param paymentRequest request for update payment
     * @return payment updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Payment> update(@PathVariable(name = "id") String id,
                                @RequestBody Payment paymentRequest) {
        return paymentService.update(id, paymentRequest);
    }

    /**
     * @param id payment id to delete
     * @return void
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return paymentService.delete(id);
    }

}
