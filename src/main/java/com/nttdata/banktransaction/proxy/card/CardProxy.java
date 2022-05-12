package com.nttdata.banktransaction.proxy.card;

import com.nttdata.banktransaction.dto.request.proxy.BankAccountRequest;
import com.nttdata.banktransaction.dto.response.proxy.BankAccountResponse;
import com.nttdata.banktransaction.enums.TransactionType;
import com.nttdata.banktransaction.exceptions.CustomException;
import com.nttdata.banktransaction.proxy.client.ClientProxy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * This class get queries external
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class CardProxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(CardProxy.class);

    @Value("${api-gateway.routes.ms-card.card}")
    private String cardURL;

    private final WebClient webClient;

    public Mono<BankAccountResponse> getCardByCci(String cci) {
        LOGGER.info("[REQUEST][URL][getCardByCci]:" + cardURL + "/cci/" + cci);
        return webClient.get()
                .uri(cardURL + "/cci/" + cci)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BankAccountResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][getCardByCci]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }

    public Mono<BankAccountResponse> updateCardBalance(String id, BankAccountRequest request,
                                                   TransactionType transactionType) {
        LOGGER.info("[REQUEST][URL][updateCardBalance]" + cardURL + "/" + id);
        LOGGER.info("[REQUEST][BODY][updateCardBalance]" + request.toString());
        return webClient.put()
                .uri(cardURL + "/" + id + "?transactionType=" + transactionType)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), BankAccountRequest.class)
                .retrieve()
                .bodyToMono(BankAccountResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][updateCardBalance]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }

}
