package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.dto.mapper.DepositMapper;
import com.nttdata.banktransaction.dto.mapper.WithdrawalMapper;
import com.nttdata.banktransaction.dto.response.DepositResponse;
import com.nttdata.banktransaction.dto.response.MovementResponse;
import com.nttdata.banktransaction.dto.response.WithdrawalResponse;
import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.model.Withdrawal;
import com.nttdata.banktransaction.service.IDepositService;
import com.nttdata.banktransaction.service.IMovementService;
import com.nttdata.banktransaction.service.IWithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovementServiceImpl implements IMovementService {

    private final IDepositService depositService;

    private final IWithdrawalService withdrawalService;

    private final DepositMapper depositMapper;

    private final WithdrawalMapper withdrawalMapper;

    /**
     * @param clientId    client id
     * @param accountType account type
     * @return a list
     */
    @Override
    public Mono<MovementResponse> findAllByClientIdAndAccountType(String clientId, String accountType) {
        Flux<DepositResponse> deposits = depositService.findAll()
                .filter(d -> d.getClientId().equals(clientId))
                .filter(d -> d.getAccountType().equals(accountType))
                .flatMap(d -> depositMapper.toMonoResponse(Mono.just(d)));
        Flux<WithdrawalResponse> withdrawals = withdrawalService.findAll()
                .filter(w -> w.getClientId().equals(clientId))
                .filter(w -> w.getAccountType().equals(accountType))
                .flatMap(w -> withdrawalMapper.toMonoResponse(Mono.just(w)));
        return Mono.just(new MovementResponse(deposits, withdrawals));
    }
}
