package com.volvadvit.springloadbalancer.config;

import com.volvadvit.springloadbalancer.supplier.DemoServerInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DemoServerInstanceConfiguration {

    @Bean
    @Primary
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServerInstanceSupplier("example-service");
    }
}
