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
    @GetMapping
    public Mono<ResponseEntity<Flux<Credit>>> getAll() {
        return Mono.just(
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(creditService.findAll()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * @param credit request to create credit
     * @return credit created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Credit>> create(@RequestBody Credit credit) {
        return creditService.create(credit)
                .map(bac -> ResponseEntity
                        .created(URI.create(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bac));
    }

    /**
     * @param id            credit id to update
     * @param creditRequest request for update credit
     * @return credit updated
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Credit>> update(@PathVariable(name = "id") String id,
                                               @RequestBody Credit creditRequest) {
        return creditService.update(id, creditRequest)
                .map(bac -> ResponseEntity
                        .created(URI.create(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bac))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * @param id credit id to delete
     * @return void
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(name = "id") String id) {
        return creditService.delete(id)
                .then(Mono.just(
                        new ResponseEntity<>(HttpStatus.NO_CONTENT)
                ));
    }

}
