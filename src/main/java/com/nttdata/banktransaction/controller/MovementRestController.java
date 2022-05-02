package com.nttdata.banktransaction.controller;

import com.nttdata.banktransaction.dto.response.MovementResponse;
import com.nttdata.banktransaction.service.IMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movements")
public class MovementRestController {

    private final IMovementService movementService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MovementResponse> getAllByClientIdAndAccountType(
            @RequestParam(name = "clientId") String clientId,
            @RequestParam(name = "accountType") String accountType) {
        return movementService.findAllByClientIdAndAccountType(clientId, accountType);
    }

}
