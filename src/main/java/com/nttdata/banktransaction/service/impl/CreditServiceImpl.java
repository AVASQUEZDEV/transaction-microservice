package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.dto.mapper.CreditMapper;
import com.nttdata.banktransaction.dto.request.CreditRequest;
import com.nttdata.banktransaction.dto.response.proxy.PersonResponse;
import com.nttdata.banktransaction.enums.PlanType;
import com.nttdata.banktransaction.exceptions.CustomException;
import com.nttdata.banktransaction.model.Credit;
import com.nttdata.banktransaction.proxy.person.PersonProxy;
import com.nttdata.banktransaction.repository.ICreditRepository;
import com.nttdata.banktransaction.service.ICreditService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

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

    //private PersonProxy personProxy = new PersonProxy();
    private final PersonProxy personProxy;

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
     * This method return one credit
     *
     * @param id request
     * @return credit
     */
    @Override
    public Mono<Credit> findById(String id) {
        return creditRepository.findById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findById]" + e.getMessage());
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "" + e));
                }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /**
     * This method creates a credits
     *
     * @param request request to create new credit
     * @return credit created
     */
    @Override
    public Mono<Credit> create(CreditRequest request) {
        return personProxy.getPersonById(request.getPersonId())
                .flatMap(per ->
                        creditRepository.findByPersonId(request.getPersonId())
                                .flatMap(c -> {
                                    String planType = per.getPersonType().getName();
                                    if (planType.equals(PlanType.Empresarial.toString())) {
                                        LOGGER.info("[" + getClass().getName() + "][create]" + planType);
                                        return creditMapper.toPostModel(request)
                                                .flatMap(creditRepository::save)
                                                .onErrorResume(e -> {
                                                    LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                                                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                                                }).switchIfEmpty(Mono.error(CustomException.notFound("Does not created credit")));
                                    } else {
                                        return Mono.error(CustomException.badRequest("The personal plan already has a credit"));
                                    }
                                }).switchIfEmpty(creditMapper.toPostModel(request)
                                        .flatMap(creditRepository::save)
                                        .onErrorResume(e -> {
                                            LOGGER.error("[" + getClass().getName() + "][create]" + e.getMessage());
                                            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request" + e));
                                        }).switchIfEmpty(Mono.error(CustomException.notFound("Does not created credit")))
                                )
                ).switchIfEmpty(Mono.error(CustomException.notFound("Does not exist client")));

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
