package br.com.felipe.poc.estudoobservability.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Service
public class GreetingService {
	
	@Autowired
	private ObservationRegistry observationRegistry;
	
	public String sayHello() {
		return Observation
				.createNotStarted("greetingService", observationRegistry)
				.observe(this::sayHelloNoOberver);
	}

	private String sayHelloNoOberver() {
		return "Hello World!";
	}
}
