package com.nttdata.bcp1.msaccount.controller;

import com.nttdata.bcp1.msaccount.model.Account;
import com.nttdata.bcp1.msaccount.service.AccountService;
import com.nttdata.bcp1.msaccount.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @GetMapping
    public Flux<Account> getAccounts(){
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable("id") String id){
        return accountService.getAccountById(id);
    }

    @GetMapping("/byCustomer/{id}")
    public Flux<Account> getByIdClient(@PathVariable("id") String idCustomer){
        return accountService.getAllByIdCustomer(idCustomer);
    }

    @PostMapping("/create")
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
        accountService.delete(id);
    }
}
