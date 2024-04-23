package org.beginnertoexpert.msscbreweryclient.web.client;

import org.beginnertoexpert.msscbreweryclient.web.config.BreweryConfig;
import org.beginnertoexpert.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class BreweryClient {

    private final String BEER_PATH_V1 ="/api/v1/beer/";

    private final BreweryConfig breweryConfig;

    private final RestTemplate restTemplate;


    public BreweryClient(BreweryConfig breweryConfig, RestTemplateBuilder restTemplateBuilder) {
        this.breweryConfig = breweryConfig;
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id){

        return restTemplate.getForObject(breweryConfig.getApiHost()+BEER_PATH_V1+id.toString(),BeerDto.class);
    }
}
