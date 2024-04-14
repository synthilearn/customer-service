package com.synthilearn.customerservice.infra.api.rest;

import java.util.UUID;

import jakarta.validation.Valid;

import com.synthilearn.commonstarter.GenericResponse;
import com.synthilearn.customerservice.app.services.CustomerService;
import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import com.synthilearn.customerservice.infra.api.rest.dto.EditUserRequest;
import com.synthilearn.securestarter.AccessToken;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer-service/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Mono<GenericResponse<CustomerDto>> getCustomerById(AccessToken token) {
        return customerService.getCustomerById(token.getId())
                .map(GenericResponse::ok);
    }

    @GetMapping("/by-email/{email}")
    public Mono<GenericResponse<CustomerDto>> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email)
                .map(GenericResponse::ok);
    }

    @PatchMapping("/{id}")
    public Mono<GenericResponse<CustomerDto>> editCustomer(@PathVariable UUID id,
                                                           @RequestBody @Valid EditUserRequest request) {
        return customerService.editCustomer(id, request)
                .map(GenericResponse::ok);
    }

}
