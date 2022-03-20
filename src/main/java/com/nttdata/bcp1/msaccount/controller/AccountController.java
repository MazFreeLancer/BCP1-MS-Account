package com.nttdata.bcp1.msaccout.controller;

import com.nttdata.bcp1.msaccout.MsAccoutApplication;
import com.nttdata.bcp1.msaccout.model.Account;
import com.nttdata.bcp1.msaccout.model.Customer;
import com.nttdata.bcp1.msaccout.service.AccountService;
import com.nttdata.bcp1.msaccout.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Flux<Account> getAccounts(){
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable("id") String id){
        return accountService.getAccountById(id);
    }

    @GetMapping("/byClient/{id}")
    public Flux<Account> getByIdClient(@PathVariable("id") String idClient){
        return accountService.getAllByIdClient(idClient);
    }

    @PostMapping
    Mono<Account> postAccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @PostMapping("/associate-card/{idAccount}/{idCard}")
    Mono<Account> associateCard(@PathVariable("idAccount") String idAccount,
                                @PathVariable("idCard") String idCard){
        return accountService.associateWithCard(idAccount, idCard);
    }

    @PutMapping
    Mono<Account> updAccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    void dltAccount(@PathVariable("id") String id){
        service.delete(id);
    }
}
