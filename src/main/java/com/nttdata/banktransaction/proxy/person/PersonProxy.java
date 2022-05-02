package com.nttdata.banktransaction.proxy.person;

import com.nttdata.banktransaction.dto.response.proxy.PersonResponse;
import com.nttdata.banktransaction.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * This class get queries external
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class PersonProxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonProxy.class);

    @Value("${microservices.person.base-url}")
    private String baseUrl;

    @Value("${microservices.person.end-point.person}")
    private String path;

    public String getCompleteURL() {
        return baseUrl + path;
    }
    //private WebClient.Builder webClient = WebClient.builder();

    private final WebClient webClient;

    public Mono<PersonResponse> getPersonById(String id) {
        return webClient.get()
                .uri(getCompleteURL() + "/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][getPersonById]" + e);
                    return Mono.error(CustomException.badRequest("The request to proxy is invalid"));
                });
    }
}
