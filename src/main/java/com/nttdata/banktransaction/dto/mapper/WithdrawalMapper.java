package com.nttdata.banktransaction.dto.mapper;

import com.nttdata.banktransaction.dto.request.WithdrawalRequest;
import com.nttdata.banktransaction.dto.response.WithdrawalResponse;
import com.nttdata.banktransaction.model.Withdrawal;
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
public class WithdrawalMapper {

    /**
     * This method convert request to model
     *
     * @param request request of withdrawal
     * @return withdrawal model
     */
    public Mono<Withdrawal> toPostModel(WithdrawalRequest request) {
        return Mono.just(
                new Withdrawal(request.getOriginAccount(),
                        request.getIssueBank(),
                        request.getAmount(),
                        AppUtil.dateFormat(new Date()))
        );
    }

    /**
     * This method convert request to model
     *
     * @param withdrawal entity
     * @param request    withdrawal request
     * @return withdrawal model
     */
    public Mono<Withdrawal> toPutModel(Withdrawal withdrawal, WithdrawalRequest request) {
        withdrawal.setAmount(request.getAmount());
        withdrawal.setTransactionDate(AppUtil.dateFormat(new Date()));
        return Mono.just(withdrawal);
    }

    /**
     * This method convert withdrawal to response
     *
     * @param withdrawal entity
     * @return converted response
     */
    public Mono<WithdrawalResponse> toMonoResponse(Mono<Withdrawal> withdrawal) {
        return withdrawal.flatMap(w -> Mono.just(
                new WithdrawalResponse(w.getId(),
                        w.getOriginAccount(),
                        w.getIssueBank(),
                        w.getAmount(),
                        w.getTransactionDate()))
        );
    }

    /**
     * This method convert a list the withdrawals to response
     *
     * @param withdrawals withdrawals list
     * @return converted response
     */
    public Flux<WithdrawalResponse> toFluxResponse(Flux<Withdrawal> withdrawals) {
        return withdrawals.flatMap(p -> toMonoResponse(Mono.just(p)));
    }

}
