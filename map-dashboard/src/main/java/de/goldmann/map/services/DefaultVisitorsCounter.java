package de.goldmann.map.services;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import de.goldmann.apps.root.services.VisitorsCounter;

@Component
public class DefaultVisitorsCounter implements VisitorsCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
	public void count() {
        counter.incrementAndGet();
    }

    @Override
	public int getCounter() {
        return counter.get();
    }
}
