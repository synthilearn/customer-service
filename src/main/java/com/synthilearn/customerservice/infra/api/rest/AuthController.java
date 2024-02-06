package com.synthilearn.customerservice.infra.api.rest;

import com.synthilearn.commonstarter.GenericResponse;
import com.synthilearn.customerservice.app.services.AuthService;
import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.api.rest.dto.DataSaveRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.EmailRequest;
import com.synthilearn.customerservice.infra.api.rest.dto.ExternalRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/customer-service/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/email-setup")
    public Mono<GenericResponse<Customer>> saveCustomerEmail(@RequestBody @Valid EmailRequest request) {
        return authService.emailSetup(request)
                .map(GenericResponse::ok);
    }

    @PostMapping("/save-data")
    public Mono<GenericResponse<Customer>> customerDataSave(@RequestBody @Valid DataSaveRequest request) {
        return authService.dataSave(request)
                .map(GenericResponse::ok);
    }

    @PostMapping("/activate")
    public Mono<GenericResponse<Customer>> customerActivate(@RequestBody @Valid EmailRequest request) {
        return authService.activate(request)
                .map(GenericResponse::ok);
    }

    @PostMapping("/external-register")
    public Mono<GenericResponse<Customer>> externalRegister(@RequestBody @Valid ExternalRegisterRequest request) {
        return authService.externalRegister(request)
                .map(GenericResponse::ok);
    }
}
