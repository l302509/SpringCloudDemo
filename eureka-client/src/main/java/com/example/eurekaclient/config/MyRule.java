package com.example.eurekaclient.config;

import java.util.List;
import java.util.Random;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

public class MyRule implements IRule {
	
	private ILoadBalancer lb;
	
	

	@Override
	public Server choose(Object obj) {
		Random r = new Random();
	      int randomNum = r.nextInt(10);
	      List<Server> servers = lb.getAllServers();
	      for(Server server: servers){
				System.out.println("Host:"+server.getHost());
				System.out.println("HostPort:"+server.getHostPort());
				System.out.println("Id:"+server.getId());
				System.out.println("Scheme:"+server.getScheme());
				System.out.println("Port:"+server.getPort());
			}
	      if(randomNum > 7) {
	         Server s = getServerByPort(servers, 2001);
	         return s;
	      }
	      return getServerByPort(servers, 2001);
	}
	
	 private Server getServerByPort(List<Server> servers, int port) {
	      for(Server s : servers) {
	         if(s.getPort() == port) {
	            return s;
	         }
	      }
	      return null;
	   }

	@Override
	public ILoadBalancer getLoadBalancer() {
		// TODO Auto-generated method stub
		return this.lb;
	}

	@Override
	public void setLoadBalancer(ILoadBalancer iloadbalancer) {
		// TODO Auto-generated method stub
		this.lb = iloadbalancer;
	}

	/*@Override
	public List<Server> getServerList() {
		// TODO Auto-generated method stub
		List<Server> servers = lb.getAllServers();
		return servers;
	}*/

}
