package gg.bayes.challenge.service.impl.processors;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroNormalDamageDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.repository.HeroNormalDamageRepository;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.HeroService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static gg.bayes.challenge.util.EventProcessorUtil.*;

@Service
@AllArgsConstructor
public class NormalDamageEventProcessor extends AbstractEventProcessor {
    private HeroService heroService;
    private HeroNormalDamageRepository damageRepository;

    private final static int TIMESTAMP_INDEX = 0;
    private final static int ATTACKER_NAME_INDEX = 1;
    private final static int VICTIM_NAME_INDEX = 3;
    private final static int DAMAGE_DEALT_INDEX = 7;

    @Override
    public void process(String event, MatchDO match) {
        if (event.contains("hits") && StringUtils.countMatches(event, "npc_dota_hero_") == 2 && event.contains("dota_unknown")) {
            String[] eventData = event.split(" ");
            HeroDO attacker = extractHeroDO(eventData[ATTACKER_NAME_INDEX], heroService);
            HeroDO victim = extractHeroDO(eventData[VICTIM_NAME_INDEX], heroService);
            Integer damageDealt = extractDamageDealt(eventData[DAMAGE_DEALT_INDEX]);
            Long durationInMillis = timestampToDurationLong(eventData[TIMESTAMP_INDEX]);

            HeroNormalDamageDO heroNormalDamageDO = HeroNormalDamageDO.builder()
                    .attacker(attacker)
                    .target(victim)
                    .damage(damageDealt)
                    .timestamp(durationInMillis)
                    .match(match)
                    .build();
            damageRepository.save(heroNormalDamageDO);

        } else if (nextProcessor != null) {
            nextProcessor.process(event, match);
        }
    }
}
