package com.nbjwl.microservice.envoy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController
{

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Demo Service";
    }

    @GetMapping("/helloUser")
    public String helloUser()
    {
        return restTemplate.getForObject("/hello", String.class);
    }

}
