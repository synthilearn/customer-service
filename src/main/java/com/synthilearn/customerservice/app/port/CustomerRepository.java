package com.synthilearn.customerservice.app.port;

import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerRepository {

    Mono<CustomerEntity> findByEmail(String email);

    Mono<Customer> findById(UUID id);
    Mono<Customer> emailSetup(String email);
    Mono<Customer> dataSave(CustomerEntity customer, DataSaveRequest request);
    Mono<Customer> activate(CustomerEntity customer);
}
