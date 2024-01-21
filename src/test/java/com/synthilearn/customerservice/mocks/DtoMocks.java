package com.synthilearn.customerservice.mocks;

import com.synthilearn.customerservice.infra.api.rest.dto.CustomerDto;
import org.jeasy.random.EasyRandom;
import reactor.core.publisher.Mono;

public class DtoMocks {

    private static final EasyRandom easyRandom = new EasyRandom();
    public static final Mono<CustomerDto> CUSTOMER_DTO = Mono.just(easyRandom.nextObject(CustomerDto.class));
}
