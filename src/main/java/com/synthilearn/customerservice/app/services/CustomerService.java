package com.synthilearn.customerservice.app.services;

import java.util.UUID;

import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import com.synthilearn.customerservice.infra.api.rest.dto.EditUserRequest;

import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerDto> getCustomerByEmail(String email);

    Mono<CustomerDto> getCustomerById(UUID id);

    Mono<CustomerDto> editCustomer(UUID id, EditUserRequest request);
}
