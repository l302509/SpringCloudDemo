package com.example.eurekaclient.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.loadbalancer.Server;
@RestController
public class DcController {
	
	private static Logger log = LoggerFactory.getLogger(DcController.class);

	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	EurekaClient eurekaClient;

	@RequestMapping("/dc")
	public String dc(HttpServletRequest request) throws InterruptedException {
		//Thread.sleep(5000L);
		//System.err.println("==========="+dc);
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			log.error(headerNames.nextElement());
		}
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
	
	public static void main(String[] args) {
		List<Map<String,String>> orginList = new ArrayList<>();
    	List<Map<String,String>> afterList = new ArrayList<>();
    	orginList.add(new HashMap(){
    		{
    			put("name", "June");
    		}
    		   
    	});
    	orginList.add(new HashMap(){
    		{
    			put("name", "June");
    		}
    		   
    	});
    	orginList.add(new HashMap(){
    		{
    			put("name", "ELL");
    		}
    		   
    	});
    	orginList.add(new HashMap(){
    		{
    			put("name", "June");
    		}
    		   
    	});
    	for(Map<String,String> map : orginList){
    		
    		String name = map.get("name");
    		System.err.println("name:"+name);
    		if(afterList.size() < 1){
    			Map<String,String> initMap = new HashMap<>();
    			initMap.put("name", name);
    			initMap.put("len", "1");
    			afterList.add(initMap);
    		}else{
    			for(Map<String,String> keyMap : afterList){
    				System.err.println("keyMap:"+JSONObject.toJSONString(keyMap));
        			if(keyMap.get("name") != null && name.equals(keyMap.get("name"))){
        				int len = Integer.valueOf(keyMap.get("len"));
        				keyMap.put("len", String.valueOf(++len));
        				break;
        			}else{
        				Map<String,String> initMap = new HashMap<>();
        				System.err.println("++addname:"+name);
            			initMap.put("name", name);
            			initMap.put("len", "1");
            			afterList.add(initMap);
            			break;
        			}
        			
        		}
    		}
    		
    		
    	}
    	
    	System.err.println("orginList:"+JSONObject.toJSONString(orginList));
    	System.err.println("afterList:"+JSONObject.toJSONString(afterList));
	}

}
