package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.model.Withdrawal;
import com.nttdata.banktransaction.service.IWithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * This controller class defines the endpoints to withdrawals
 *
 * @author Alcibar Vasquesz
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/withdrawals")
public class WithdrawalRestController {

    private final IWithdrawalService withdrawalService;

    /**
     * @return list of withdrawals
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Withdrawal> getAll() {
        return withdrawalService.findAll();
    }

    /**
     * @param withdrawal request to create withdrawal
     * @return withdrawal created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Withdrawal> create(@RequestBody Withdrawal withdrawal) {
        return withdrawalService.create(withdrawal);
    }

    /**
     * @param id                withdrawal id to update
     * @param withdrawalRequest request for update withdrawal
     * @return withdrawal updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Withdrawal> update(@PathVariable(name = "id") String id,
                                   @RequestBody Withdrawal withdrawalRequest) {
        return withdrawalService.update(id, withdrawalRequest);
    }

    /**
     * @param id withdrawal id to delete
     * @return void
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return withdrawalService.delete(id);
    }

}
