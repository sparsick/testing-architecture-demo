package com.github.sparsick.springbootexample.hero.universum;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;

@Component
public class HeroRepositoryExperimentWrapper implements HeroRepository {

    private HeroRepositoryExperiment allHeroExperiment;
    private HeroRepositoryExperiment searchHeroExperiment;
    private final UniqueHeroRepository uniqueHeroRepository;
    private final DuplicateHeroRepository duplicateHeroRepository;

    public HeroRepositoryExperimentWrapper(UniqueHeroRepository uniqueHeroRepository, DuplicateHeroRepository duplicateHeroRepository, MeterRegistry meterRegistry) {
        this.uniqueHeroRepository = uniqueHeroRepository;
        this.duplicateHeroRepository = duplicateHeroRepository;
        searchHeroExperiment = new HeroRepositoryExperiment("search.hero", meterRegistry);
        allHeroExperiment = new HeroRepositoryExperiment("all.hero", meterRegistry);

    }

    @PostConstruct
    public void init() {
        allHeroRepositories().forEach( heroRepo -> {
            heroRepo.addHero(new Hero("Batman", "Gotham City", ComicUniversum.DC_COMICS));
            heroRepo.addHero(new Hero("Superman", "Metropolis", ComicUniversum.DC_COMICS));
        });
    }

    private Collection<HeroRepository> allHeroRepositories(){
        Collection<HeroRepository> heroRepositories = new HashSet<>();
        heroRepositories.add(uniqueHeroRepository);
        heroRepositories.add(duplicateHeroRepository);
        return heroRepositories;
    }


    @Override
    public String getName() {
        return "Wrapper";
    }

    @Override
    public void addHero(Hero hero) {
        allHeroRepositories().forEach( heroRepository -> heroRepository.addHero(hero));
    }

    @Override
    public Collection<Hero> allHeros() {
        try {
            return allHeroExperiment.run(uniqueHeroRepository::allHeros, duplicateHeroRepository::allHeros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Hero> findHerosBySearchCriteria(String searchCriteria) {
        try {
            return searchHeroExperiment.run( () -> uniqueHeroRepository.findHerosBySearchCriteria(searchCriteria),
                        () -> duplicateHeroRepository.findHerosBySearchCriteria(searchCriteria) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
