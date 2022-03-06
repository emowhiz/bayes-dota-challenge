package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.domainobject.SpellDO;
import gg.bayes.challenge.repository.SpellRepository;
import gg.bayes.challenge.service.SpellService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpellServiceImpl implements SpellService {
    private SpellRepository spellRepository;

    @Override
    public SpellDO getOrInsertAndGet(String spell) {
        return spellRepository.findByName(spell).orElseGet(() -> spellRepository.save(SpellDO.builder().name(spell).build()));
    }
}
