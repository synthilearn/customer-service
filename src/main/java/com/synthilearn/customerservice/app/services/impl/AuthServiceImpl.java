package com.synthilearn.customerservice.app.services.impl;

import com.synthilearn.customerservice.app.port.CustomerRepository;
import com.synthilearn.customerservice.app.services.AuthService;
import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.ExternalRegisterRequest;
import com.synthilearn.customerservice.infra.api.rest.exception.CustomerException;
import com.synthilearn.customerservice.infra.api.rest.dto.EmailRequest;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> emailSetup(EmailRequest request) {
        return customerRepository.findByEmail(request.email())
                .singleOptional()
                .flatMap(customerOpt -> {
                    checkCustomerNotExists(customerOpt, request.email());
                    return customerRepository.emailSetup(request.email());
                });
    }

    @Override
    public Mono<Customer> dataSave(DataSaveRequest request) {
        return customerRepository.findByEmail(request.getEmail())
                .singleOptional()
                .flatMap(customerOpt -> {
                    CustomerEntity customer = getCustomerOrDie(customerOpt, request.getEmail());
                    checkStatus(customer.getStatus(), RegisterStatus.EMAIL_SET, request.getEmail());
                    return customerRepository.dataSave(customer, request);
                });
    }

    @Override
    public Mono<Customer> activate(EmailRequest request) {
        return customerRepository.findByEmail(request.email())
                .singleOptional()
                .flatMap(customerOpt -> {
                    CustomerEntity customer = getCustomerOrDie(customerOpt, request.email());
                    checkStatus(customer.getStatus(), RegisterStatus.DATA_SAVED, request.email());
                    return customerRepository.activate(customer);
                });
    }

    @Override
    public Mono<Customer> externalRegister(ExternalRegisterRequest request) {
        return customerRepository.findByEmail(request.getEmail())
                .singleOptional()
                .flatMap(customerOpt -> {
                    checkCustomerNotExists(customerOpt, request.getEmail());
                    return customerRepository.externalRegister(request);
                });
    }

    private void checkCustomerNotExists(Optional<CustomerEntity> customerOpt, String email) {
        if (customerOpt.isPresent()) {
            log.error("Customer with email: {} already exists", email);
            throw CustomerException.alreadyExistsByEmail(email);
        }
    }

    private void checkStatus(RegisterStatus actual, RegisterStatus expected, String email) {
        if (actual != expected) {
            log.error("Customer with email: {} has invalid status for data save: {}", email, actual);
            throw CustomerException.invalidStatus(email, actual);
        }
    }

    private CustomerEntity getCustomerOrDie(Optional<CustomerEntity> customerOpt, String email) {
        return customerOpt
                .orElseThrow(() -> {
                    log.error("Customer with email: {} not found", email);
                    throw CustomerException.notFoundByEmail(email);
                });
    }
}
