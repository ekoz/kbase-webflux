package com.eastrobot.kbasewebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class KbaseWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbaseWebfluxApplication.class, args);
	}
}
