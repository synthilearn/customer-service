package com.synthilearn.customerservice.app.port;

import java.util.UUID;

import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.EditUserRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.ExternalRegisterRequest;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;

import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<CustomerEntity> findByEmail(String email);

    Mono<Customer> findById(UUID id);

    Mono<Customer> emailSetup(String email);

    Mono<Customer> dataSave(CustomerEntity customer, DataSaveRequest request);

    Mono<Customer> activate(CustomerEntity customer);

    Mono<Customer> externalRegister(ExternalRegisterRequest request);

    Mono<Customer> editCustomer(EditUserRequest request, UUID id);
}
