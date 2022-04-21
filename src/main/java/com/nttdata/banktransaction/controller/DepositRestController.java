package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.service.IDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * This controller class defines the endpoints to deposits
 *
 * @author Alcibar Vasquesz
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/deposits")
public class DepositRestController {

    private final IDepositService depositService;

    /**
     * @return list of deposits
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Deposit>>> getAll() {
        return Mono.just(
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(depositService.findAll()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * @param deposit request to create deposit
     * @return deposit created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Deposit>> create(@RequestBody Deposit deposit) {
        return depositService.create(deposit)
                .map(bac -> ResponseEntity
                        .created(URI.create(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bac));
    }

    /**
     * @param id             deposit id to update
     * @param depositRequest request for update deposit
     * @return deposit updated
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Deposit>> update(@PathVariable(name = "id") String id,
                                                @RequestBody Deposit depositRequest) {
        return depositService.update(id, depositRequest)
                .map(bac -> ResponseEntity
                        .created(URI.create(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bac))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * @param id deposit id to delete
     * @return void
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(name = "id") String id) {
        return depositService.delete(id)
                .then(Mono.just(
                        new ResponseEntity<>(HttpStatus.NO_CONTENT)
                ));
    }

}
