package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.dto.mapper.DepositMapper;
import com.nttdata.banktransaction.dto.request.DepositRequest;
import com.nttdata.banktransaction.dto.request.proxy.BankAccountRequest;
import com.nttdata.banktransaction.enums.TransactionType;
import com.nttdata.banktransaction.exceptions.CustomException;
import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.proxy.bankaccount.BankAccountProxy;
import com.nttdata.banktransaction.repository.IDepositRepository;
import com.nttdata.banktransaction.service.IDepositService;
import com.nttdata.banktransaction.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


/**
 * This class defines the service of deposits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class DepositServiceImpl implements IDepositService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepositServiceImpl.class);

    private final IDepositRepository depositRepository;

    private final DepositMapper depositMapper;

    private final BankAccountProxy bankAccountProxy;

    /**
     * This method returns a list of deposits
     *
     * @return deposits list
     */
    @Override
    public Flux<Deposit> findAll() {
        return depositRepository.findAll()
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findAll]" + e.getMessage());
                    return Mono.error(CustomException.internalServerError("Internal Server Error"));
                });
    }

    /**
     * This method creates a deposits
     *
     * @param request request to create new deposit
     * @return deposit created
     */
    @Override
    public Mono<Deposit> create(DepositRequest request) {
        return bankAccountProxy.getBankAccountByCCI(request.getDestinationAccount())
                .flatMap(ba -> bankAccountProxy
                        .balanceUpdate(ba.getId(),
                                new BankAccountRequest(request.getAmount()),
                                TransactionType.DEPOSIT)
                        .flatMap(bac -> depositMapper
                                .toPostModel(request)
                                .flatMap(depositRepository::save)
                        )
                ).onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(depositMapper.toPostModel(request)
                        .flatMap(depositRepository::save));
    }

    /**
     * This method updates a deposit
     *
     * @param id      deposit id to update
     * @param request request to update deposit
     * @return deposit updated
     */
    @Override
    public Mono<Deposit> update(String id, DepositRequest request) {
        return findAll()
                .filter(d -> d.getId().equals(id))
                .single()
                .flatMap(d -> depositMapper.toPutModel(d, request)
                        .flatMap(depositRepository::save))
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][update]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Deposit not found")));
    }

    /**
     * This method delete a deposit
     *
     * @param id deposit id to delete
     * @return void
     */
    @Override
    public Mono<Void> delete(String id) {
        return depositRepository.deleteById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][delete]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                });
    }
}
