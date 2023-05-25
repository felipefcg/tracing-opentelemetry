package br.com.felipe.poc.estudoobservability.config;

import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationTextPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ObservationTextPublisherConfiguration {

	public ObservationHandler<Observation.Context> observationTextPublisher() {
		return new ObservationTextPublisher(log::info);
	}
}
