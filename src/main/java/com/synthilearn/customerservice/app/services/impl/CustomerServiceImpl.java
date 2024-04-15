package com.synthilearn.customerservice.app.services.impl;

import com.synthilearn.customerservice.app.port.CustomerRepository;
import com.synthilearn.customerservice.app.services.CustomerService;
import com.synthilearn.customerservice.infra.api.rest.dto.EditUserRequest;
import com.synthilearn.customerservice.infra.api.rest.exception.CustomerException;
import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import com.synthilearn.customerservice.infra.api.rest.mapper.CustomerDtoMapper;
import com.synthilearn.securestarter.AccessToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public Mono<CustomerDto> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerDtoMapper::map)
                .switchIfEmpty(Mono.error(CustomerException.notFoundByEmail(email)))
                .doOnError(error -> log.error("Customer not found by email: {}", email));
    }

    @Override
    public Mono<CustomerDto> getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(customerDtoMapper::map)
                .switchIfEmpty(Mono.error(CustomerException.notFoundById(id)))
                .doOnError(error -> log.error("Customer not found by id: {}", id));
    }

    @Override
    public Mono<CustomerDto> editCustomer(AccessToken accessToken, EditUserRequest request) {
        return customerRepository.editCustomer(request, accessToken.getId())
                .map(customerDtoMapper::map);
    }

}
