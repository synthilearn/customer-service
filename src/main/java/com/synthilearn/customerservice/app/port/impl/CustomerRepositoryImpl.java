package com.synthilearn.customerservice.app.port.impl;

import com.synthilearn.customerservice.app.port.CustomerRepository;
import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.domain.RegistrationType;
import com.synthilearn.customerservice.domain.Role;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.EditUserRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.ExternalRegisterRequest;
import com.synthilearn.customerservice.infra.api.rest.exception.CustomerException;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import com.synthilearn.customerservice.infra.persistence.jpa.mapper.CustomerEntityMapper;
import com.synthilearn.customerservice.infra.persistence.jpa.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;


    @Override
    public Mono<CustomerEntity> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email);
    }

    @Override
    public Mono<Customer> findById(UUID id) {
        return customerJpaRepository.findById(id)
                .map(customerEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Customer> emailSetup(String email) {
        return customerJpaRepository.save(emailSetupEnrichCustomer(email))
                .map(customerEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Customer> dataSave(CustomerEntity customer, DataSaveRequest request) {
        return customerJpaRepository.save(dataSaveEnrichCustomer(customer, request))
                .map(customerEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Customer> activate(CustomerEntity customer) {
        return customerJpaRepository.save(customer.toBuilder()
                        .status(RegisterStatus.ACTIVE)
                        .updatedDate(ZonedDateTime.now())
                        .build())
                .map(customerEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Customer> externalRegister(ExternalRegisterRequest request) {
        return customerJpaRepository.save(emailSetupEnrichCustomer(request.getEmail()).toBuilder()
                        .status(RegisterStatus.ACTIVE)
                        .registrationType(RegistrationType.EXTERNAL)
                        .name(request.getName())
                        .build())
                .map(customerEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Customer> editCustomer(EditUserRequest request, UUID id) {
        return customerJpaRepository.findById(id)
                .switchIfEmpty(Mono.error(CustomerException.notFoundById(id)))
                .map(customer -> customer.toBuilder()
                        .name(request.name())
                        .surname(request.surname())
                        .updatedDate(ZonedDateTime.now())
                        .build())
                .flatMap(customer -> customerJpaRepository.save(customer)
                        .map(customerEntityMapper::map));
    }

    private CustomerEntity emailSetupEnrichCustomer(String email) {
        return CustomerEntity.builder()
                .email(email)
                .id(UUID.randomUUID())
                .role(Role.USER)
                .status(RegisterStatus.EMAIL_SET)
                .creationDate(ZonedDateTime.now())
                .newRecord(true)
                .updatedDate(ZonedDateTime.now())
                .registrationType(RegistrationType.INTERNAL)
                .build();
    }

    private CustomerEntity dataSaveEnrichCustomer(CustomerEntity customer, DataSaveRequest request) {
        return customer.toBuilder()
                .status(RegisterStatus.DATA_SAVED)
                .updatedDate(ZonedDateTime.now())
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .build();
    }
}
