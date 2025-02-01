package com.volvadvit.springloadbalancer.supplier;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

record DemoServerInstanceSupplier(String serviceId) implements ServiceInstanceListSupplier {

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 64436, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 64443, false)));
    }

}
