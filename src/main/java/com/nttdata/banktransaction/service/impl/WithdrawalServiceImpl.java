package com.nttdata.banktransaction.service.impl;

import com.nttdata.banktransaction.model.Withdrawal;
import com.nttdata.banktransaction.repository.IWithdrawalRepository;
import com.nttdata.banktransaction.service.IWithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines the service of withdrawal
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class WithdrawalServiceImpl implements IWithdrawalService {

    private final IWithdrawalRepository withdrawalRepository;

    /**
     * This method returns a list of withdrawal
     *
     * @return withdrawal list
     */
    @Override
    public Flux<Withdrawal> findAll() {
        return withdrawalRepository.findAll();
    }

    /**
     * This method creates a withdrawal
     *
     * @param withdrawalRequest request to create new withdrawal
     * @return withdrawal created
     */
    @Override
    public Mono<Withdrawal> create(Withdrawal withdrawalRequest) {
        return withdrawalRepository.save(withdrawalRequest);
    }

    /**
     * This method updates a withdrawal
     *
     * @param id                withdrawal id to update
     * @param withdrawalRequest request to update withdrawal
     * @return withdrawal updated
     */
    @Override
    public Mono<Withdrawal> update(String id, Withdrawal withdrawalRequest) {
        return findAll()
                .filter(w -> w.getId().equals(id))
                .single()
                .flatMap(w -> {
                    w.setAmount(withdrawalRequest.getAmount());
                    return withdrawalRepository.save(w);
                });
    }

    /**
     * This method delete a withdrawal
     *
     * @param id withdrawal id to delete
     * @return void
     */
    @Override
    public Mono<Void> delete(String id) {
        return withdrawalRepository.deleteById(id);
    }

}
