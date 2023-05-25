package br.com.felipe.poc.estudoobservability.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServerHttpObservationFilter;

import io.micrometer.observation.ObservationRegistry;

@Configuration
public class ObservationFilterConfiguration {

	@ConditionalOnBean(ObservationRegistry.class)
	@ConditionalOnMissingBean(ServerHttpObservationFilter.class)
	@Bean
	public ServerHttpObservationFilter observationFilter (ObservationRegistry registry) {
		return new ServerHttpObservationFilter(registry);
	}
}
