package com.synthilearn.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class Customer {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private RegistrationType registrationType;
    private RegisterStatus status;
    private Role role;

    @JsonIgnore
    private boolean created;
}
