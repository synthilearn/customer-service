package com.synthilearn.customerservice.infra.api.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record EditUserRequest(
        @NotBlank String name,
        @NotBlank String surname
) {
}
