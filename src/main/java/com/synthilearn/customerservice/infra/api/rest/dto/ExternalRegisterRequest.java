package com.synthilearn.customerservice.infra.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRegisterRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
