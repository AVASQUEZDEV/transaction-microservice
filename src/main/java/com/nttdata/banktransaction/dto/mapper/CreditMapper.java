package com.nttdata.banktransaction.dto.mapper;

import com.nttdata.banktransaction.dto.request.CreditRequest;
import com.nttdata.banktransaction.dto.response.CreditResponse;
import com.nttdata.banktransaction.model.Credit;
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
public class CreditMapper {

    /**
     * This method convert request to model
     *
     * @param request request of credit
     * @return credit model
     */
    public Mono<Credit> toPostModel(CreditRequest request) {
        return Mono.just(
                new Credit(request.getClientId(), request.getAmount(),
                        request.getFeesQuantity(), request.getExpirationDate(), CreditStatus.PENDIENTE,
                        AppUtil.dateFormat(new Date()), AppUtil.dateFormat(new Date()))
        );
    }

    /**
     * This method convert request to model
     *
     * @param credit  entity
     * @param request credit request
     * @return credit model
     */
    public Mono<Credit> toPutModel(Credit credit, CreditRequest request) {
        credit.setCreditStatus(request.getCreditStatus());
        credit.setUpdatedAt(AppUtil.dateFormat(new Date()));
        return Mono.just(credit);
    }

    /**
     * This method convert credit to response
     *
     * @param credit entity
     * @return converted response
     */
    public Mono<CreditResponse> toMonoResponse(Mono<Credit> credit) {
        return credit.flatMap(c -> Mono.just(
                new CreditResponse(c.getId(), c.getClientId(), c.getAmount(), c.getFeesAmount(), c.getFeesQuantity(),
                        c.getExpirationDate(), c.getCreditStatus(), c.getCreatedAt(), c.getCreatedAt()))
        );
    }

    /**
     * This method convert a list the credits to response
     *
     * @param credits credits list
     * @return converted response
     */
    public Flux<CreditResponse> toFluxResponse(Flux<Credit> credits) {
        return credits.flatMap(c -> toMonoResponse(Mono.just(c)));
    }

}
