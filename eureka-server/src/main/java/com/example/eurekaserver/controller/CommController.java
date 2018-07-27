package com.example.eurekaserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

@RestController
public class CommController {
	/*@Autowired
	private ILoadBalancer iLoadBalancer;
	
	@RequestMapping("getServerList")
	public List<Server> getServerList(){
		List<Server> servers = iLoadBalancer.getAllServers();		
		return servers;		
	}*/

}
