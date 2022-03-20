package com.nttdata.bcp1.msaccout.service;

import com.nttdata.bcp1.msaccout.model.Customer;
import com.nttdata.bcp1.msaccout.service.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl {

    @Value("${backend.eureka.customer.get-by-id}")
    private String urlGetCustomerById;

    @Autowired
    @Qualifier("wcLoadBalanced")
    private WebClient.Builder webClientBuilder;

    public Mono<Customer> getCustomerById(String id) {
        return webClientBuilder
                .clientConnector(RestUtils.getDefaultClientConnector())
                .build()
                .get()
                .uri(urlGetCustomerById,id)
                .retrieve()
                .bodyToMono(Customer.class);
    }
}
