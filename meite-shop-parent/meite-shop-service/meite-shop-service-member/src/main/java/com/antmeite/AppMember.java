package com.antmeite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.spring4all.swagger.EnableSwagger2Doc;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@EnableFeignClients
public class AppMember {
	public static void main(String[] args) {
		SpringApplication.run(AppMember.class, args);
	}
}
