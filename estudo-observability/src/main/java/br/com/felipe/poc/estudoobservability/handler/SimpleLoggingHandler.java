package br.com.felipe.poc.estudoobservability.handler;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleLoggingHandler implements ObservationHandler<Observation.Context> {
	
	@Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }

    @Override
    public void onStart(Observation.Context context) {
        log.info("Starting");
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        log.info("Scope opened");
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        log.info("Scope closed");
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("Stopping");
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("Error");
    }
}
