package com.example.eurekaclient.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.loadbalancer.Server;
@RestController
public class DcController {

	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	EurekaClient eurekaClient;

	@GetMapping("/dc")
	public String dc() throws InterruptedException {
		//Thread.sleep(5000L);
		String services = "Services: " + discoveryClient.getServices();
		System.out.println(services);
		return services;
	}
	@GetMapping("/serverList")
	public List<Server> serverList() {
		Applications applications = eurekaClient.getApplications();
		if(applications == null)
            return Collections.emptyList();
		List registered = applications.getRegisteredApplications();
        List names = new ArrayList();
        List<Server> services = new ArrayList();
        Iterator iterator = registered.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Application app = (Application)iterator.next();
            System.err.println(JSONObject.toJSONString(app));
            if(!app.getInstances().isEmpty()){
            	List<InstanceInfo> infolist = app.getInstances();
            	for(InstanceInfo info : infolist){
            		Server server = new Server(info.getHostName());
            		services.add(server);
            		System.err.println("Host:"+info.getHostName());
            		System.err.println("IPAddr:"+info.getIPAddr());
            	}
                names.add(app.getName().toLowerCase());
            }
            	
        } while(true);
		
		return services;
	}

}
