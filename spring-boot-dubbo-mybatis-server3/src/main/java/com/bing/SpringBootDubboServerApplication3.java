package com.bing;

import org.mvnsearch.spring.boot.dubbo.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootDubboServerApplication3 extends SpringBootServletInitializer implements CommandLineRunner {
	@Value("${spring.default.val}")
	private String springDefaultVal;
	@Value("${spring.profile.val}")
	private String springProfileVal;
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboServerApplication3.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.profiles("test");
		return super.configure(builder);
	}*/

	@Override
	public void run(String... args) throws Exception {
		System.out.println(springDefaultVal);
		System.out.println(springProfileVal);
	}
}
