package com.synthilearn.customerservice.app.services;

import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerService {

    Mono<CustomerDto> getCustomerByEmail(String email);
    Mono<CustomerDto> getCustomerById(UUID id);
}
