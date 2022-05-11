package com.example.springbatchstudy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchStudyApplication {

	public static void main(String[] args) {
		String[] realArgs = {"customerUpdateFile=classpath:data/customer_update_shuffled.csv"};
		SpringApplication.run(SpringBatchStudyApplication.class, realArgs);
	}

}
