package com.github.sparsick.springbootexample.hero.universum;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UniqueHeroRepository implements HeroRepository {

    private Set<Hero> heroes = new HashSet<>();

    @Override
    public String getName() {
        return "Unique";
    }

    @Override
    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    @Override
    public Collection<Hero> allHeros() {
        return new HashSet<>(heroes);
    }

    @Override
    public Collection<Hero> findHerosBySearchCriteria(String searchCriteria) {
        return heroes.stream().filter( hero -> StringUtils.containsIgnoreCase(hero.toString(), searchCriteria)).collect(Collectors.toSet());
    }

}
