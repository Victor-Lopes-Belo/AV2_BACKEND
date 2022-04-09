package br.com.tech4me.tech4music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Tech4musicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tech4musicApplication.class, args);
	}

}
