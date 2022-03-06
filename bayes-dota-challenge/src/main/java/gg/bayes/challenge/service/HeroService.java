package gg.bayes.challenge.service;

import gg.bayes.challenge.domainobject.HeroDO;

public interface HeroService {
    HeroDO getOrInsertAndGet(String hero);
}
