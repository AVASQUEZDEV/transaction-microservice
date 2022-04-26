package com.nttdata.banktransaction.dto.mapper;

import com.nttdata.banktransaction.dto.request.PaymentRequest;
import com.nttdata.banktransaction.dto.response.PaymentResponse;
import com.nttdata.banktransaction.model.Payment;
import com.nttdata.banktransaction.util.AppUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * This class convert request and response
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Service
public class PaymentMapper {

    /**
     * This method convert request to model
     *
     * @param request request of payment
     * @return payment model
     */
    public Mono<Payment> toPostModel(PaymentRequest request) {
        return Mono.just(new Payment(request.getAmount(), AppUtil.dateFormat(new Date())));
    }

    /**
     * This method convert request to model
     *
     * @param payment entity
     * @param request payment request
     * @return payment model
     */
    public Mono<Payment> toPutModel(Payment payment, PaymentRequest request) {
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(AppUtil.dateFormat(new Date()));
        return Mono.just(payment);
    }

    /**
     * This method convert payment to response
     *
     * @param payment entity
     * @return converted response
     */
    public Mono<PaymentResponse> toMonoResponse(Mono<Payment> payment) {
        return payment.flatMap(p -> Mono.just(
                new PaymentResponse(p.getId(), p.getAmount(), p.getPaymentDate()))
        );
    }

    /**
     * This method convert a list the payments to response
     *
     * @param payments payments list
     * @return converted response
     */
    public Flux<PaymentResponse> toFluxResponse(Flux<Payment> payments) {
        return payments.flatMap(p -> toMonoResponse(Mono.just(p)));
    }

}
