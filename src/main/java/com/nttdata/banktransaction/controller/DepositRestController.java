package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.service.IDepositService;
import com.nttdata.banktransaction.service.impl.DepositServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DepositServiceImpl.class);

    private final IDepositService depositService;

    /**
     * @return list of deposits
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Deposit> getAll() {
        return depositService.findAll();
    }

    /**
     * @param deposit request to create deposit
     * @return deposit created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Deposit> create(@RequestBody Deposit deposit) {
        return depositService.create(deposit);
    }

    /**
     * @param id             deposit id to update
     * @param depositRequest request for update deposit
     * @return deposit updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Deposit> update(@PathVariable(name = "id") String id,
                                @RequestBody Deposit depositRequest) {
        return depositService.update(id, depositRequest);
    }

    /**
     * @param id deposit id to delete
     * @return void
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return depositService.delete(id);
    }

}
