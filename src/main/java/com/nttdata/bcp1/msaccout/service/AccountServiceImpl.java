package com.nttdata.bcp1.msaccout.service;

import com.nttdata.bcp1.msaccout.MsAccoutApplication;
import com.nttdata.bcp1.msaccout.model.Account;
import com.nttdata.bcp1.msaccout.model.Customer;
import com.nttdata.bcp1.msaccout.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{
    private static final Logger logger = LogManager.getLogger(MsAccoutApplication.class);

    @Autowired
    AccountRepository accountRepository;


    @Autowired
    CustomerServiceImpl customerService;

    @Override
    @Transactional
    public Mono<Account> create(Account a) {
        Mono<Customer> customerMono = customerService.getCustomerById(a.getIdCustomer());
        Mono<Long> countAccountMono = accountRepository.countAccountByIdCustomer(a.getIdCustomer());
        logger.info("****** CUSTOMER MONO : " + customerMono.toString());

        return customerMono.flatMap(customer -> {
            logger.info("Customer Type = " + customer.getCustomerType().toString());
            if(customer.getCustomerType().toString().equals("PERSONAL")){
                //FLIJO PARA REGISTRO DE CUENTAS DE CLIENTES PERSONALES
                logger.info("Ingreso Primer IF");
                return countAccountMono.flatMap(cam ->{
                    if(cam>0){
                        logger.info("ENTRA AL MONO ERROR");
                        return Mono.error(new
                                Exception("The customer already has a registered account"));
                    }else{
                        logger.info("GUARDA EL ACCOUNT");
                        return accountRepository.save(a);
                    }
                });
            }else if(customer.getCustomerType().toString().equals("BUSINESS")){
                logger.info("Graba validando cuenta empresarial");
                if(a.getAccountType().toString().equals("CURRENT_ACCOUNT")){
                    return accountRepository.save(a);
                }else{
                    return Mono.error(new
                            Exception("Business customer cannot create this type of accounts"));
                }
            }else{
                return null;
            }

        });
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


    @Override
    public Flux<Account> findAllByIdCustomer(String idCustomer) {
        return accountRepository.findAllByIdCustomer(idCustomer);
    }
}
