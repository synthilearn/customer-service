package com.synthilearn.customerservice.infra.api.rest.exception;

import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.infra.api.rest.exception.parent.GenericException;
import com.synthilearn.customerservice.infra.api.rest.util.ErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CustomerException extends GenericException {

    private CustomerException(String message, HttpStatus status, Integer code) {
        super(message, status, code);
    }

    public static CustomerException notFoundById(UUID id) {
        return new CustomerException(String.format("Customer with id: %s not found", id), HttpStatus.NOT_FOUND, ErrorCodes.ERROR);
    }

    public static CustomerException notFoundByEmail(String email) {
        return new CustomerException(String.format("Customer with email: %s not found", email), HttpStatus.NOT_FOUND, ErrorCodes.ERROR);
    }

    public static CustomerException alreadyExistsByEmail(String email) {
        return new CustomerException(String.format("Customer with email: %s already exists", email), HttpStatus.BAD_REQUEST, ErrorCodes.ERROR);
    }

    public static CustomerException invalidStatus(String email, RegisterStatus status) {
        return new CustomerException(String.format("Customer with email: %s has invalid status for data save: %s", email, status), HttpStatus.BAD_REQUEST, 3010);
    }
}
