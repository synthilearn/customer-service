package com.synthilearn.customerservice.infra.api.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveCustomerDataRequest(@NotBlank(message = "Birth date couldn't be empty") String birthDate,
                                      @NotBlank(message = "Name couldn't be empty") String name,
                                      @NotBlank(message = "Surname couldn't be empty") String surname,
                                      @NotBlank(message = "Password couldn't be empty") String password,
                                      @NotBlank(message = "Email couldn't be empty") String email) {
}
