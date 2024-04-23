package org.beginnertoexpert.msscbreweryclient.web.client;

import org.beginnertoexpert.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;
    @Test
    void getBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void testSaveBeer(){
        UUID id = UUID.fromString("19062300-3964-4b5d-88cb-09598f1cb4f2");
        BeerDto beerDto = BeerDto.builder().beerName("NewBeer").id(id).build();

        URI uri = client.saveNewBeer(beerDto);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        UUID uuid = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder().id(uuid).build();

        client.updateBeer(uuid,beerDto);

    }
}