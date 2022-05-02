package com.nttdata.banktransaction.proxy.bankaccount;

import com.nttdata.banktransaction.dto.request.proxy.BankAccountRequest;
import com.nttdata.banktransaction.dto.response.proxy.BankAccountResponse;
import com.nttdata.banktransaction.enums.TransactionType;
import com.nttdata.banktransaction.exceptions.CustomException;
import com.nttdata.banktransaction.proxy.person.PersonProxy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * This class get queries external
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class BankAccountProxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonProxy.class);

    @Value("${microservices.bank-account.base-url}")
    private String baseUrl;

    @Value("${microservices.bank-account.end-point.bank-account}")
    private String path;

    public String getCompleteURL() {
        return baseUrl + path;
    }

    private final WebClient webClient;

    public Mono<BankAccountResponse> getBankAccountByCCI(String id) {
        LOGGER.info("[REQUEST][URL][getBankAccountByCCI]:" + getCompleteURL() + "/cci/" + id);
        return webClient.get()
                .uri(getCompleteURL() + "/cci/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BankAccountResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][getBankAccountByCCI]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }

    public Mono<BankAccountResponse> balanceUpdate(String id, BankAccountRequest request,
                                                   TransactionType transactionType) {
        LOGGER.info("[REQUEST][URL][balanceUpdate]" + getCompleteURL() + "/" + id);
        LOGGER.info("[REQUEST][BODY][balanceUpdate]" + request.toString());
        return webClient.put()
                .uri(getCompleteURL() + "/" + id + "?transactionType=" + transactionType)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), BankAccountRequest.class)
                .retrieve()
                .bodyToMono(BankAccountResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][getBankAccountByCCI]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }

}
