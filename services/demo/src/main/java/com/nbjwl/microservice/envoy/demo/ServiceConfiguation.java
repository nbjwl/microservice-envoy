package com.nbjwl.microservice.envoy.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ServiceConfiguation
{
    @Bean
    public RestTemplate demoRestTemplate()
    {
        return new RestTemplateBuilder().rootUri("http://localhost:10000")
                .additionalInterceptors((request, body, execution) ->
                {

                    HttpHeaders headers = request.getHeaders();
                    headers.set("SERVICE", "service:user");
                    return execution.execute(request, body);
                }).build();
    }
}