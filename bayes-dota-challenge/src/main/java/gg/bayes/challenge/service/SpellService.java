package gg.bayes.challenge.service;

import gg.bayes.challenge.domainobject.SpellDO;

public interface SpellService {
    SpellDO getOrInsertAndGet(String spell);
}
