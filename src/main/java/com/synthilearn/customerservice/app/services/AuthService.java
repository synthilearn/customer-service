package com.synthilearn.customerservice.app.services;

import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.EmailRequest;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<Customer> emailSetup(EmailRequest request);
    Mono<Customer> dataSave(DataSaveRequest request);
    Mono<Customer> activate(EmailRequest request);
}
