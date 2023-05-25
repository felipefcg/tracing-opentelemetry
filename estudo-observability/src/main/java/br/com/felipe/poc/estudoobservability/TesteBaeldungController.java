package br.com.felipe.poc.estudoobservability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.poc.estudoobservability.executor.TesteBaeldung;

@RestController
@RequestMapping("teste-baeldung")
public class TesteBaeldungController {

	@Autowired
	private TesteBaeldung testeBaeldung;
	
	@GetMapping("observation-e-observationRegistry")
	public void observationEObservationRegistry() {
		testeBaeldung.observationEObservationRegistry();
	}
	
	@GetMapping("observationHandler")
	public void observationHandler () {
		testeBaeldung.observationHandler();
	}
	
	@GetMapping("temporizador-e-contador")
	public void temporizadorEContador() {
		testeBaeldung.temporizadorEContador();
	}
	
	@GetMapping("utilizando-integracao-com-o-SpringActuator")
	public void utilizandoIntegracaoComSpringActuator() {
		testeBaeldung.utilizandoIntegracaoComSpringActuator();
	}
}
