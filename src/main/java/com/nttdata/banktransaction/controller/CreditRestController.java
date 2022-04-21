package com.nttdata.banktransaction.controller;

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

    /**
     * @return list of credits
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Credit> getAll() {
        return creditService.findAll();
    }

    /**
     * @param credit request to create credit
     * @return credit created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Credit> create(@RequestBody Credit credit) {
        return creditService.create(credit);
    }

    /**
     * @param id            credit id to update
     * @param creditRequest request for update credit
     * @return credit updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Credit> update(@PathVariable(name = "id") String id,
                               @RequestBody Credit creditRequest) {
        return creditService.update(id, creditRequest);
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
