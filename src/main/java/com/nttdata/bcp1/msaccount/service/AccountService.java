package com.nttdata.bcp1.msaccout.service;

import com.nttdata.bcp1.msaccout.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<Account> getAll();
    Flux<Account> getAllByIdClient(String idCustomer);
    Mono<Account> associateWithCard(String idAccount, String idCard);
    Mono<Account> getAccountById(String id);
    Mono<Account> getAccountByIdClientAndTypeAccount(String idClient, String typeAccount);
    Mono<Account> save(Account account);
    void delete(String id);
}
