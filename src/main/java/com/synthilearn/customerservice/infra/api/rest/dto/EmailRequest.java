package com.synthilearn.customerservice.infra.api.rest.dto;

import io.micrometer.observation.annotation.Observed;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Observed
public record EmailRequest(@NotBlank(message = "Email couldn't be empty")
                               @Email(message = "Email isn't valid") String email) {}
