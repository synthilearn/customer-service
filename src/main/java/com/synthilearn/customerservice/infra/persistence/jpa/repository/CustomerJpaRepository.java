package com.synthilearn.customerservice.infra.persistence.jpa.repository;

import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.infra.persistence.jpa.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends ReactiveCrudRepository<CustomerEntity, UUID> {

    Mono<CustomerEntity> findByEmail(String email);
}
