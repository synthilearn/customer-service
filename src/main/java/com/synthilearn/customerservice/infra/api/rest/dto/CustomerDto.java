package com.synthilearn.customerservice.infra.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.domain.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private RegistrationType registrationType;
    private RegisterStatus status;
    @JsonIgnore
    private boolean isCreated;
}
