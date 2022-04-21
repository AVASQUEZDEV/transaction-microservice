package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.model.Deposit;
import com.nttdata.banktransaction.repository.IDepositRepository;
import com.nttdata.banktransaction.service.IDepositService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "" + e));
                });
    }

    /**
     * This method creates a deposits
     *
     * @param depositRequest request to create new deposit
     * @return deposit created
     */
    @Override
    public Mono<Deposit> create(Deposit depositRequest) {
        return depositRepository.save(depositRequest)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
    }

    /**
     * This method updates a deposit
     *
     * @param id             deposit id to update
     * @param depositRequest request to update deposit
     * @return deposit updated
     */
    @Override
    public Mono<Deposit> update(String id, Deposit depositRequest) {
        return findAll()
                .filter(d -> d.getId().equals(id))
                .single()
                .flatMap(d -> {
                    d.setAmount(depositRequest.getAmount());
                    return depositRepository.save(d);
                }).onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][update]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
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
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                });
    }
}
