package com.lifepill.pharmacyservice.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.lifepill.pharmacyservice.service.PharmacyService;

@Profile("test")
@Configuration
public class PharmacyServiceTestConfiguration {
    @Bean
    @Primary
    public PharmacyService pharmacyService() {
        return Mockito.mock(PharmacyService.class);
    }
}
