package com.nttdata.banktransaction.dto.mapper;

import com.nttdata.banktransaction.dto.request.DepositRequest;
import com.nttdata.banktransaction.dto.response.DepositResponse;
import com.nttdata.banktransaction.model.Deposit;
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
public class DepositMapper {

    /**
     * This method convert request to model
     *
     * @param request request of deposit
     * @return deposit model
     */
    public Mono<Deposit> toPostModel(DepositRequest request) {
        return Mono.just(
                new Deposit(request.getDestinationAccount(),
                        request.getOriginAccount(),
                        request.getAmount(),
                        request.getIssueBank(),
                        AppUtil.dateFormat(new Date())
                )
        );
    }

    /**
     * This method convert request to model
     *
     * @param deposit  entity
     * @param request deposit request
     * @return deposit model
     */
    public Mono<Deposit> toPutModel(Deposit deposit, DepositRequest request) {
        deposit.setAmount(request.getAmount());
        deposit.setTransactionDate(AppUtil.dateFormat(new Date()));
        return Mono.just(deposit);
    }

    /**
     * This method convert deposit to response
     *
     * @param deposit entity
     * @return converted response
     */
    public Mono<DepositResponse> toMonoResponse(Mono<Deposit> deposit) {
        return deposit.flatMap(d -> Mono.just(
                new DepositResponse(d.getId(),
                        d.getDestinationAccount(),
                        d.getOriginAccount(),
                        d.getAmount(),
                        d.getIssueBank(),
                        d.getTransactionDate()
                ))
        );
    }

    /**
     * This method convert a list the deposits to response
     *
     * @param deposits deposits list
     * @return converted response
     */
    public Flux<DepositResponse> toFluxResponse(Flux<Deposit> deposits) {
        return deposits.flatMap(d -> toMonoResponse(Mono.just(d)));
    }
    
}
