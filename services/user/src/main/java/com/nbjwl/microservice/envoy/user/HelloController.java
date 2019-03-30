package com.nbjwl.microservice.envoy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/hello")
    public String hello()
    {
        return "Hello User Service";
    }

    @GetMapping("/hello/demo")
    public String helloDemo()
    {
        return "Hello Demo";
    }

}
