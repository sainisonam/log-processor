package com.assignment.logprocessor;

import com.assignment.logprocessor.service.LogProcessorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class LogprocessorApplication implements CommandLineRunner {

	@Autowired
	private LogProcessorServiceImplementation logProcessorServiceImplementation;

	public static void main(String[] args) {
		SpringApplication.run(LogprocessorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logProcessorServiceImplementation.parseLogFile(args[0]);
	}
}