package org.beginnertoexpert.msscbreweryclient.web.client;

import org.beginnertoexpert.msscbreweryclient.web.config.BreweryConfig;
import org.beginnertoexpert.msscbreweryclient.web.model.BeerDto;
import org.beginnertoexpert.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {

    private final String BEER_PATH_V1 ="/api/v1/customer";

    private final BreweryConfig breweryConfig;

    private final RestTemplate restTemplate;

    public CustomerClient(BreweryConfig breweryConfig, RestTemplateBuilder restTemplateBuilder) {
        this.breweryConfig = breweryConfig;
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID id){

        return restTemplate.getForObject(breweryConfig.getApiHost()+BEER_PATH_V1+"/"+id.toString(),CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(breweryConfig.getApiHost()+BEER_PATH_V1,customerDto);
    }

    public void updateCustomer(UUID uuid,CustomerDto customerDto){
        restTemplate.put(breweryConfig.getApiHost()+BEER_PATH_V1+"/"+uuid.toString(),customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(breweryConfig.getApiHost()+BEER_PATH_V1+"/"+uuid.toString());
    }
}
