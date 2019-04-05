package com.nbjwl.microservice.envoy.demo;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ServiceConfiguation
{
    private ClientHttpRequestFactory getClientHttpRequestFactory()
    {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean
    public RestTemplate demoRestTemplate()
    {
        return new RestTemplateBuilder().rootUri("http://localhost:10000")
                .additionalInterceptors((request, body, execution) ->
                {

                    HttpHeaders headers = request.getHeaders();
                    headers.set("Host", "user-service");
                    return execution.execute(request, body);
                }).requestFactory(() -> getClientHttpRequestFactory()).build();
    }
}
