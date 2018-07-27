package com.example.eurekaclient.config;

import java.util.List;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

public interface MyBaseRule extends IRule{
	
	public List<Server> getServerList();

}
