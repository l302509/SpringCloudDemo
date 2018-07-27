package com.example.eurekaserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;

@Configuration
public class BaseConfig {
	
/*
	    @Bean
	    public ILoadBalancer iLoadBalancer(){
	        return LoadBalancerBuilder.newBuilder().buildLoadBalancerFromConfigWithReflection();
	    }*/

}
