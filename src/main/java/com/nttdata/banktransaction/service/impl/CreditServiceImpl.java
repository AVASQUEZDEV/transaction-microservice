package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.model.Credit;
import com.nttdata.banktransaction.repository.ICreditRepository;
import com.nttdata.banktransaction.service.ICreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    private final ICreditRepository creditRepository;

    /**
     * This method returns a list of credits
     *
     * @return credits list
     */
    @Override
    public Flux<Credit> findAll() {
        return creditRepository.findAll();
    }

    /**
     * This method creates a credits
     *
     * @param creditRequest request to create new credit
     * @return credit created
     */
    @Override
    public Mono<Credit> create(Credit creditRequest) {
        return creditRepository.save(creditRequest);
    }

    /**
     * This method updates a credit
     *
     * @param id            credit id to update
     * @param creditRequest request to update credit
     * @return credit updated
     */
    @Override
    public Mono<Credit> update(String id, Credit creditRequest) {
        return findAll()
                .filter(c -> c.getId().equals(id))
                .single()
                .flatMap(c -> {
                    c.setAmount(creditRequest.getAmount());
                    return creditRepository.save(c);
                });
    }

    /**
     * This method delete a credit
     *
     * @param id credit id to delete
     * @return void
     */
    @Override
    public Mono<Void> delete(String id) {
        return creditRepository.deleteById(id);
    }

}
