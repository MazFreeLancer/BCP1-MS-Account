package com.nttdata.bcp1.msaccout.repository;

import com.nttdata.bcp1.msaccout.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findAllByIdCustomer(String idCustomer);
    Mono<Long> countAccountByIdCustomer(String idCustomer);
}
