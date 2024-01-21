package com.synthilearn.customerservice.infra.api.rest.mapper;

import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    CustomerDto map(Customer customer);
    CustomerDto map(CustomerEntity customer);
}
