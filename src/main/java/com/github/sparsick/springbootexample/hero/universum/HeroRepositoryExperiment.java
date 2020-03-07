package com.github.sparsick.springbootexample.hero.universum;

import com.github.rawls238.scientist4j.Experiment;
import com.github.rawls238.scientist4j.Result;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

public class HeroRepositoryExperiment extends Experiment<Collection<Hero>> {

    private final AtomicLong controlDuration;
    private AtomicLong candidateDuration;
    private Counter noMatchCounter;
    private Counter matchCounter;

    public HeroRepositoryExperiment(String name, MeterRegistry meterRegistry) {
        super(name);
        candidateDuration = meterRegistry.gauge("hero.repository.experiment." + name + ".candidate.duration", new AtomicLong(0));
        controlDuration = meterRegistry.gauge("hero.repository.experiment." + name + ".control.duration", new AtomicLong(0));
        noMatchCounter = meterRegistry.counter("hero.repository.experiment." + name + ".runs", "match", "no");
        matchCounter = meterRegistry.counter("hero.repository.experiment." + name + ".runs", "match", "yes");

    }

    @Override
    protected boolean compareResults(Collection<Hero> controlVal, Collection<Hero> candidateVal) {
        return controlVal.size() == candidateVal.size() && controlVal.containsAll(candidateVal);
    }

    @Override
    protected void publish(Result<Collection<Hero>> result) {
        candidateDuration.set(result.getCandidate().get().getDuration());
        controlDuration.set(result.getControl().getDuration());


        result.getMatch().ifPresent(match -> {
            if (match) {
                matchCounter.increment();
            } else {
                noMatchCounter.increment();
            }
        });


    }
}
