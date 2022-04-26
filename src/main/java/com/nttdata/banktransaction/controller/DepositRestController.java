package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.dto.mapper.DepositMapper;
import com.nttdata.banktransaction.dto.request.DepositRequest;
import com.nttdata.banktransaction.dto.response.DepositResponse;
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

    private final DepositMapper depositMapper;

    /**
     * @return list of deposits
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<DepositResponse> getAll() {
        return depositMapper.toFluxResponse(depositService.findAll());
    }

    /**
     * @param request request to create deposit
     * @return deposit created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DepositResponse> create(@RequestBody DepositRequest request) {
        return depositMapper.toMonoResponse(depositService.create(request));
    }

    /**
     * @param id             deposit id to update
     * @param request request for update deposit
     * @return deposit updated
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DepositResponse> update(@PathVariable(name = "id") String id,
                                @RequestBody DepositRequest request) {
        return depositMapper.toMonoResponse(depositService.update(id, request));
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
