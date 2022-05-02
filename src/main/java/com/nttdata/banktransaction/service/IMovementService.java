package com.nttdata.banktransaction.service;

import com.nttdata.banktransaction.dto.response.MovementResponse;
import reactor.core.publisher.Mono;

public interface IMovementService {

    Mono<MovementResponse> findAllByClientIdAndAccountType(String clientId, String accountType);

}
