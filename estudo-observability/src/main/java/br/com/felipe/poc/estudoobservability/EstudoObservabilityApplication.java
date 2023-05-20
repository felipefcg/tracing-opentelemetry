package br.com.felipe.poc.estudoobservability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.felipe.poc.estudoobservability.executor.TesteBaeldung;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EstudoObservabilityApplication {

	private static final String PULAR_LINHA = "\n\n";
	
	public static void main(String[] args) {
		SpringApplication.run(EstudoObservabilityApplication.class, args);
		log.info("App Iniciada");
		TesteBaeldung.observationEObservationRegistry();
		log.info(PULAR_LINHA);
		TesteBaeldung.observationHandler();
		log.info(PULAR_LINHA);
		TesteBaeldung.temporizadorEContador();
		log.info("App Finalizada");
	}

}
