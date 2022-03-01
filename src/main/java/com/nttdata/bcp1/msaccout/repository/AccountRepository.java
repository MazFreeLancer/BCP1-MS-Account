package com.nttdata.bcp1.msaccout.repository;

import com.nttdata.bcp1.msaccout.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}
