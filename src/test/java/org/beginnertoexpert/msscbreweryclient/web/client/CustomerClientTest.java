package org.beginnertoexpert.msscbreweryclient.web.client;

import org.beginnertoexpert.msscbreweryclient.web.model.BeerDto;
import org.beginnertoexpert.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;
    @Test
    void getCustomerById() {
        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {
        UUID id = UUID.fromString("19062300-3964-4b5d-88cb-09598f1cb4f2");
        CustomerDto customerDto = CustomerDto.builder().name("ABC").id(id).build();

        URI uri = client.saveNewCustomer(customerDto);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateCustomer() {
        UUID uuid = UUID.randomUUID();
        CustomerDto customerDto = CustomerDto.builder().id(uuid).build();

        client.updateCustomer(uuid,customerDto);

    }

    @Test
    void deleteCustomer() {
        UUID uuid = UUID.randomUUID();
        client.deleteCustomer(uuid);
    }
}