package com.example.eurekaconsumerribbonhystrix.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DcController {
	
	private static Logger log = LoggerFactory.getLogger(DcController.class);
	
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
        	
        	log.info("consumer======start");
        	
        	/*HttpHeaders headers = new HttpHeaders();
        	headers.set("HelloWorld", "HelloWorld");
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
        	restTemplate.exchange("http://eureka-client/dc", HttpMethod.GET, entity, String.class).getBody();*/
            return restTemplate.getForObject("http://eureka-client/dc", String.class);
        }

        public String fallback() {
            return "fallback";
        }

    }

}
