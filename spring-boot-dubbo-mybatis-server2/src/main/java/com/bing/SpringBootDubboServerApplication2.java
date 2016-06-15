package com.bing;

import org.mvnsearch.spring.boot.dubbo.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootDubboServerApplication2 {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboServerApplication2.class, args);
	}
}
