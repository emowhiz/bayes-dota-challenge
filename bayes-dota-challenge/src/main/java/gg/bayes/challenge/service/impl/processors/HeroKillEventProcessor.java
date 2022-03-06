package gg.bayes.challenge.service.impl.processors;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroKillDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.repository.HeroKillRepository;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.HeroService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static gg.bayes.challenge.util.EventProcessorUtil.extractHeroDO;
import static gg.bayes.challenge.util.EventProcessorUtil.timestampToDurationLong;

@Service
@AllArgsConstructor
public class HeroKillEventProcessor extends AbstractEventProcessor {
    private HeroService heroService;
    private HeroKillRepository heroKillRepository;

    private final static int KILLER_NAME_INDEX = 1;
    private final static int VICTIM_NAME_INDEX = 5;
    private final static int TIMESTAMP_INDEX = 0;

    @Override
    public void process(String event, MatchDO match) {
        if (event.contains("is killed by") && StringUtils.countMatches(event, "npc_dota_hero_") == 2) {
            String[] eventData = event.split(" ");
            HeroDO killerDo = extractHeroDO(eventData[KILLER_NAME_INDEX], heroService);
            HeroDO victimDo = extractHeroDO(eventData[VICTIM_NAME_INDEX], heroService);
            Long durationInMillis = timestampToDurationLong(eventData[TIMESTAMP_INDEX]);

            HeroKillDO heroKillDO = HeroKillDO.builder()
                    .killer(killerDo)
                    .victim(victimDo)
                    .match(match)
                    .timestamp(durationInMillis)
                    .build();
            heroKillRepository.save(heroKillDO);
        } else if (nextProcessor != null) {
            nextProcessor.process(event, match);
        }
    }
}
