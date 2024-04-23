package org.beginnertoexpert.msscbreweryclient.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sfg.brewery",ignoreUnknownFields = false)
@Configuration
public class BreweryConfig {

    @Getter
    @Setter
    private String apiHost;

}
