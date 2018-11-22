package com.antmeite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.spring4all.swagger.EnableSwagger2Doc;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@EnableFeignClients
@MapperScan(basePackages = "com.antmeite.mapper")
public class AppStock {
	public static void main(String[] args) {
		SpringApplication.run(AppStock.class, args);
	}
}
