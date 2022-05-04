package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.dto.mapper.CreditMapper;
import com.nttdata.banktransaction.dto.request.CreditRequest;
import com.nttdata.banktransaction.dto.response.CreditResponse;
import com.nttdata.banktransaction.model.Credit;
import com.nttdata.banktransaction.service.ICreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * This controller class defines the endpoints to credits
 *
 * @author Alcibar Vasquesz
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/credits")
public class CreditRestController {

    private final ICreditService creditService;

    private final CreditMapper creditMapper;

    /**
     * @return list of credits
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CreditResponse> getAll() {
        return creditMapper.toFluxResponse(creditService.findAll());
    }


    /**
     * @return list of credits
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CreditResponse> getByClientId(@PathVariable(name = "clientId") String clientId) {
        return creditMapper.toFluxResponse(creditService.findByClientId(clientId));
    }

    /**
     * @param request request to create credit
     * @return credit created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CreditResponse> create(@RequestBody CreditRequest request) {
        return creditMapper.toMonoResponse(creditService.create(request));
    }

    /**
     * @param id      credit id to update
     * @param request request for update credit
     * @return credit updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CreditResponse> update(@PathVariable(name = "id") String id,
                                       @RequestBody CreditRequest request) {
        return creditMapper.toMonoResponse(creditService.update(id, request));
    }

    /**
     * @param id credit id to delete
     * @return void
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return creditService.delete(id);
    }

}
