**spring-services** \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|->**spring-config** `Spring Cloud Config Server`, `Spring Security`, `Spring Web` \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|->**spring-loadbalancer** `Spring Cloud Load Balancer`, `Spring WebFlux` \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|->**eureka-server** `Eureka Server` \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-->  **spring-gateway** `Spring Cloud Gateway`, `Spring OAuth Client`, `Eureka Client`, `Spring WebFlux` \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-->  **spring-data**    `Spring Kafka`, `Spring Cloud Config`, `Spring Data`, `Spring Web`, `Eureka Client` \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-->  **kafka-producer** `Spring Kafka`, `Spring Web`, `Spring OAuth Resource Server`, `Eureka Client`
___
Relations:\
`spring-config`  <-> `spring-data` (with `cloudconfig` profile)\
`kafka-producer` <-> `spring-data`\
`kafka-producer` <-> `spring-loadbalancer` (`kafka-producer` as a server here)
___
Consoles:
1. `eureka-server`      : http://localhost:8761
2. `Keycloak Console`   : http://localhost:7080
3. `Kafka-UI`           : http://localhost:8090
___
Resources:
1. `spring-gateway`     : http://localhost:8765
2. `kafka-producer`     : http://localhost:8765/kafka
3. `spring-loadbalancer`: http://localhost:8765/loadbalancer/server
4. `spring-loadbalancer`: http://localhost:8085/loadbalancer/client
5. `spring-config`      : http://localhost:8888/spring-data-cloudconfig
___
Access PostgreSQL via console: docker exec -it postgres-container psql -U myuser postgres