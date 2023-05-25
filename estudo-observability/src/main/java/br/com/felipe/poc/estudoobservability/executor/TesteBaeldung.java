package br.com.felipe.poc.estudoobservability.executor;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.poc.estudoobservability.handler.SimpleLoggingHandler;
import br.com.felipe.poc.estudoobservability.service.GreetingService;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Statistic;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Felipe Goriano
 * @link https://www.baeldung.com/spring-boot-3-observability
 * Essa classe foi criada para teste do artigo <b>Observability with Spring Boot 3</b>
 * 
 */

@Slf4j
@Component
public class TesteBaeldung {
	
	private static final String LINHA_DIVISORIA = "--------------------------";
	
	@Autowired
	private GreetingService greetingService;
	
	public void observationEObservationRegistry() {
		log.info("Executando observationEObservationRegistry");
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		Observation observation = Observation.createNotStarted("sample", observationRegistry);
		
		forma1DeExecutarUmObservation(observation);
		log.info(LINHA_DIVISORIA);
		forma2DeExecutarUmObservation(observation);
	}

	public void observationHandler() {
		log.info("Executando observationHandler");
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		
		utilizandoSimpleLoggingHandler(observationRegistry);
		log.info(LINHA_DIVISORIA);
		utilizandoObservationTextPublisher(observationRegistry);
	}
	
	public void temporizadorEContador() {
		log.info("Executnado temporizadorEContador (Timer Samples and Counters");
		MeterRegistry meterRegistry = new SimpleMeterRegistry();
		ObservationHandler<Context> defaultMeterObservationHandler = new DefaultMeterObservationHandler(meterRegistry);
		
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		observationRegistry
			.observationConfig()
			.observationHandler(defaultMeterObservationHandler);
		
		// ... observe using Observation with name "sample"
		Observation observation = Observation.createNotStarted("sample", observationRegistry);
		forma1DeExecutarUmObservation(observation);

		// fetch maximum duration of the named observation
		Optional<Double> maximumDuration = meterRegistry.getMeters().stream()
		  .filter(m -> "sample".equals(m.getId().getName()))
		  .flatMap(m -> StreamSupport.stream(m.measure().spliterator(), false))
		  .filter(ms -> ms.getStatistic() == Statistic.MAX)
		  .findFirst()
		  .map(Measurement::getValue)
		;
		
		log.info("Tempo máximo de execução: {}", maximumDuration.get());
	}

	public void utilizandoIntegracaoComSpringActuator() {
		log.info(greetingService.sayHello());		
	}

	private void forma1DeExecutarUmObservation(Observation observation) {
		observation.start();
		try {
			log.info("Forma 1 de executar um Observation");
		} catch (Exception e) {
			observation.error(e);
		} finally {
			observation.stop();
		}
		
	}
	
	private void forma2DeExecutarUmObservation(Observation observation) {
		observation.observe( () -> log.info("Forma 2 de executar um Observation"));
	}
	
	private void utilizandoSimpleLoggingHandler(ObservationRegistry observationRegistry) {
		log.info("Utilizando o SimpleLoggingHandler");
		observationRegistry
			.observationConfig()
			.observationHandler(new SimpleLoggingHandler());
		
		Observation observation = Observation.createNotStarted("sample", observationRegistry);
		forma2DeExecutarUmObservation(observation);
		
		
	}

	private void utilizandoObservationTextPublisher(ObservationRegistry observationRegistry) {
		log.info("Utilizando o ObservationTextPublisher");
		observationRegistry
			.observationConfig()
			.observationHandler(new ObservationTextPublisher(System.out::println));
		
		Observation observation = Observation.createNotStarted("sample", observationRegistry);
		forma2DeExecutarUmObservation(observation);
	}


}
