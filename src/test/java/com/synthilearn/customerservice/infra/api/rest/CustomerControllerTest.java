package com.synthilearn.customerservice.infra.api.rest;

import com.synthilearn.customerservice.app.services.CustomerService;
import com.synthilearn.customerservice.infra.api.rest.exception.CustomerException;
import com.synthilearn.customerservice.infra.api.rest.util.ErrorCodes;
import com.synthilearn.customerservice.mocks.CommonMocks;
import com.synthilearn.customerservice.mocks.DtoMocks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private CustomerService customerService;
    @Value("${test.token}")
    private String token;
    private final String BASE_MAPPING = "/customer-service/v1/customer";

    @Test
    public void getCustomerById_Success() {
        when(customerService.getCustomerById(any()))
                .thenReturn(DtoMocks.CUSTOMER_DTO);
        webTestClient.get()
                .uri(BASE_MAPPING)
                .header(AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
        verify(customerService).getCustomerById(any());
    }

    @Test
    public void getCustomerById_NotFound() {
        when(customerService.getCustomerById(any()))
                .thenThrow(CustomerException.notFoundById(CommonMocks.CUSTOMER_ID));
        webTestClient.get()
                .uri(BASE_MAPPING)
                .header(AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("resultData").doesNotExist()
                .jsonPath("code").isEqualTo(ErrorCodes.ERROR)
                .jsonPath("message").exists();
        verify(customerService).getCustomerById(any());
    }

    @Test
    public void getCustomerByEmail_Success() {
        when(customerService.getCustomerByEmail(any()))
                .thenReturn(DtoMocks.CUSTOMER_DTO);
        webTestClient.get()
                .uri(BASE_MAPPING + "/by-email/{email}", CommonMocks.CUSTOMER_EMAIL)
                .exchange()
                .expectStatus().isOk();
        verify(customerService).getCustomerByEmail(any());
    }

    @Test
    public void getCustomerByEmail_NotFound() {
        when(customerService.getCustomerByEmail(any()))
                .thenThrow(CustomerException.notFoundByEmail(CommonMocks.CUSTOMER_EMAIL));
        webTestClient.get()
                .uri(BASE_MAPPING + "/by-email/{email}", CommonMocks.CUSTOMER_EMAIL)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("resultData").doesNotExist()
                .jsonPath("code").isEqualTo(ErrorCodes.ERROR)
                .jsonPath("message").exists();
        verify(customerService).getCustomerByEmail(any());
    }
}