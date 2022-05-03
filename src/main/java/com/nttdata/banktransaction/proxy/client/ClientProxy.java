package com.nttdata.banktransaction.proxy.client;

import com.nttdata.banktransaction.dto.response.proxy.ClientResponse;
import com.nttdata.banktransaction.exceptions.CustomException;
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
public class ClientProxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientProxy.class);

    @Value("${microservices.client.base-url}")
    private String baseUrl;

    @Value("${microservices.client.end-point.client}")
    private String path;

    public String getCompleteURL() {
        return baseUrl + path;
    }
    //private WebClient.Builder webClient = WebClient.builder();

    private final WebClient webClient;

    public Mono<ClientResponse> getClientById(String id) {
        LOGGER.info("[REQUEST][URL][getBankAccountByCCI]:" + getCompleteURL() + "/" + id);
        return webClient.get()
                .uri(getCompleteURL() + "/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ClientResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][getClientById]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }
}
