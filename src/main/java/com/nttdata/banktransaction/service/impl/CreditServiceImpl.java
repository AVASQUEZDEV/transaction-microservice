package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.dto.mapper.CreditMapper;
import com.nttdata.banktransaction.dto.request.CreditRequest;
import com.nttdata.banktransaction.model.Credit;
import com.nttdata.banktransaction.repository.ICreditRepository;
import com.nttdata.banktransaction.service.ICreditService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines the service of credits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements ICreditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditServiceImpl.class);

    private final ICreditRepository creditRepository;

    private final CreditMapper creditMapper;

    /**
     * This method returns a list of credits
     *
     * @return credits list
     */
    @Override
    public Flux<Credit> findAll() {
        return creditRepository.findAll()
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findAll]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "" + e));
                });
    }

    /**
     * This method creates a credits
     *
     * @param request request to create new credit
     * @return credit created
     */
    @Override
    public Mono<Credit> create(CreditRequest request) {
        return creditMapper.toPostModel(request)
                .flatMap(creditRepository::save)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /**
     * This method updates a credit
     *
     * @param id      credit id to update
     * @param request request to update credit
     * @return credit updated
     */
    @Override
    public Mono<Credit> update(String id, CreditRequest request) {
        return findAll()
                .filter(c -> c.getId().equals(id))
                .single()
                .flatMap(c -> creditMapper.toPutModel(c, request))
                    .flatMap(creditRepository::save)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][update]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /**
     * This method delete a credit
     *
     * @param id credit id to delete
     * @return void
     */
    @Override
    public Mono<Void> delete(String id) {
        return creditRepository.deleteById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][delete]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                });
    }

}
