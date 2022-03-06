package gg.bayes.challenge.service.impl.processors;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroSpellDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.domainobject.SpellDO;
import gg.bayes.challenge.repository.HeroSpellRepository;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.HeroService;
import gg.bayes.challenge.service.SpellService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static gg.bayes.challenge.util.EventProcessorUtil.*;

@Service
@AllArgsConstructor
public class SpellCastEventProcessor extends AbstractEventProcessor {
    private HeroService heroService;
    private SpellService spellService;
    private HeroSpellRepository heroSpellRepository;

    private final static int TIMESTAMP_INDEX = 0;
    private final static int HERO_NAME_INDEX = 1;
    private final static int SPELL_NAME_INDEX = 4;
    private final static int SPELL_LEVEL_INDEX = 6;

    @Override
    public void process(String event, MatchDO match) {
        if (event.contains("casts ability")) {
            String[] eventData = event.split(" ");
            HeroDO heroDO = extractHeroDO(eventData[HERO_NAME_INDEX], heroService);
            SpellDO spellDO = extractSpellDO(eventData[SPELL_NAME_INDEX], spellService);
            Integer spellLevel = extractSpellLevel(eventData[SPELL_LEVEL_INDEX]);
            Long durationInMillis = timestampToDurationLong(eventData[TIMESTAMP_INDEX]);

            HeroSpellDO heroSpellDO = HeroSpellDO.builder()
                    .hero(heroDO)
                    .spell(spellDO)
                    .level(spellLevel)
                    .match(match)
                    .timestamp(durationInMillis)
                    .build();
            heroSpellRepository.save(heroSpellDO);
        } else if (nextProcessor != null) {
            nextProcessor.process(event, match);
        }
    }
}
