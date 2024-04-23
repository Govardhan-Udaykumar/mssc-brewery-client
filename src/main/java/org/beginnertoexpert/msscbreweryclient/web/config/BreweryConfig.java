package org.beginnertoexpert.msscbreweryclient.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sfg.brewery",ignoreUnknownFields = false)
@Configuration
public class BreweryConfig {

    private String apiHost;

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
