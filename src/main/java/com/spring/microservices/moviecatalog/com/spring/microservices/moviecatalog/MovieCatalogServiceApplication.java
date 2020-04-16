package com.spring.microservices.moviecatalog.com.spring.microservices.moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MovieCatalogServiceApplication {
    @LoadBalanced
    @Bean// we can put it anywhere not necc in this class.
    public RestTemplate getRestTemplate() {
        // earlier we were making new rest template in catalog class
        // so everytime method was called a new instance was made which is wrong so we are creating a bean of it.
        return new RestTemplate();
    }

    /*@Bean
    public WebClient.Builder getWebClientBuilder() {

        return WebClient.builder();
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
