package com.synthilearn.customerservice.infra.persistence.jpa.mapper;

import com.synthilearn.customerservice.domain.Customer;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    Customer map(CustomerEntity customer);
}
