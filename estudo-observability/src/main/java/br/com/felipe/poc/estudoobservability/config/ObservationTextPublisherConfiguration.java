package br.com.felipe.poc.estudoobservability.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationTextPublisher;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Felipe Goriano
 * @link https://www.baeldung.com/spring-boot-3-observability
 * Essa classe foi criado para integrar o Micrometer com o Spring Actuator, utilizando como documentação o item 3.1  do artigo <b>Observabilidade com Spring Boot 3</b>.
 * O Spring Actuator contém um <i>ObservationAutoConfiguration</i> que prove uma instância injetável de ObservationRegistry (se ainda não existir) e configura
 * um <i>ObservationHandlers </i> para coletar métricas e traces. 
 * Por exemplo, podemos usar o Registry para criar uma observação personalizada dentro de um serviço, além disso ele registra os <b><i>Beans</i></b> <i>ObservationHandler</i>
 * no <i>ObservationRegistry</i>. Só precisamos fornecer o Bean conforme abaixo.
 * 
 */

@Slf4j
@Configuration
public class ObservationTextPublisherConfiguration {

	@Bean
	public ObservationHandler<Observation.Context> observationTextPublisher() {
		return new ObservationTextPublisher(log::info);
	}
}
