package com.nttdata.bcp1.msaccout.service;

import com.nttdata.bcp1.msaccout.model.Account;
import com.nttdata.bcp1.msaccout.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Mono<Account> create(Account a) {
        return accountRepository.save(a);
    }

    @Override
    public Mono<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> update(Account a) {
        return accountRepository.save(a);
    }

    @Override
    public Mono<Void> delete(String id) {
        return accountRepository.deleteById(id);
    }
}
