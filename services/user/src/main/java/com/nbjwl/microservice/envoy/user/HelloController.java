package com.nbjwl.microservice.envoy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

@RestController
public class HelloController
{
    @GetMapping("/hello")
    public String hello() throws UnknownHostException
    {
        return MessageFormat.format( "Hello User Service {0}", Inet4Address.getLocalHost().getHostAddress());
    }

}
