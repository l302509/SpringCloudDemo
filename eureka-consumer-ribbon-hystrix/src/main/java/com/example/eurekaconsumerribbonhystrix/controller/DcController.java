package com.example.eurekaconsumerribbonhystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DcController {
	
	@Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc() {
        return consumerService.consumer();
    }
    
    @Component
    class ConsumerService {

        @Autowired
        RestTemplate restTemplate;

        @HystrixCommand(fallbackMethod = "fallback")
        public String consumer() {
        	HttpHeaders headers = new HttpHeaders();
        	headers.set("HelloWorld", "HelloWorld");
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
        	restTemplate.exchange("http://eureka-client/dc", HttpMethod.GET, entity, String.class).getBody();
            return restTemplate.getForObject("http://eureka-client/dc", String.class);
        }

        public String fallback() {
            return "fallback";
        }

    }

}
