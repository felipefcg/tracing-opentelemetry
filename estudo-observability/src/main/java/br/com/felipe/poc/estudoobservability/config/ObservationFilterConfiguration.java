package br.com.felipe.poc.estudoobservability.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServerHttpObservationFilter;

import io.micrometer.observation.ObservationRegistry;

/**
 * 
 * @author Felipe Goriano
 * @link https://www.baeldung.com/spring-boot-3-observability
 * Essa classe foi criado para integrar o Micrometer com o Spring Actuator, utilizando como documentação o item 3.2  do artigo <b>Observabilidade com Spring Boot 3</b>.
 * Para MVC e WebFlux, existem filtros que podem ser usados ​​para observações do servidor HTTP:
 * 	<ul>
 * 		<li>org.springframework.web.filter.ServerHttpObservationFilter para Spring MVC</li>
 * 		<li>org.springframework.web.filter.reactive.ServerHttpObservationFilter para WebFlux</li>
 * <ul>
 * Quando o Actuator faz parte do nosso aplicativo, esses filtros já estão cadastrados e ativos. Se não, precisamos configurá-los, conforme essa classe
 * Podemos encontrar mais detalhes sobre a integração de observabilidade no Spring Web na <a href="https://www.baeldung.com/spring-boot-3-observability">documentação</a>
 */

@Configuration
public class ObservationFilterConfiguration {

	@ConditionalOnBean(ObservationRegistry.class)
	@ConditionalOnMissingBean(ServerHttpObservationFilter.class)
	@Bean
	public ServerHttpObservationFilter observationFilter (ObservationRegistry registry) {
		return new ServerHttpObservationFilter(registry);
	}
}
