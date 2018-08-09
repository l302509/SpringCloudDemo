package com.example.configserverdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerDbApplication.class, args);
	}
}
