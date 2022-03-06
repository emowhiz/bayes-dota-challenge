package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.repository.HeroRepository;
import gg.bayes.challenge.service.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {
    private HeroRepository heroRepository;

    @Override
    public HeroDO getOrInsertAndGet(String hero) {
      return heroRepository.findByName(hero).orElseGet(() -> heroRepository.save(HeroDO.builder().name(hero).build()));
    }
}
