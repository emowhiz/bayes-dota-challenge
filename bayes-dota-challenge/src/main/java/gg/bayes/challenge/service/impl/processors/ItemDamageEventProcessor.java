package gg.bayes.challenge.service.impl.processors;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroItemDamageDO;
import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.repository.HeroItemDamageRepository;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.HeroService;
import gg.bayes.challenge.service.ItemService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static gg.bayes.challenge.util.EventProcessorUtil.*;

@Service
@AllArgsConstructor
public class ItemDamageEventProcessor extends AbstractEventProcessor {
    private HeroService heroService;
    private ItemService itemService;
    private HeroItemDamageRepository damageRepository;

    private final static int TIMESTAMP_INDEX = 0;
    private final static int ATTACKER_NAME_INDEX = 1;
    private final static int VICTIM_NAME_INDEX = 3;
    private final static int ITEM_NAME_INDEX = 5;
    private final static int DAMAGE_DEALT_INDEX = 7;

    @Override
    public void process(String event, MatchDO match) {
        if (event.contains("hits") && StringUtils.countMatches(event, "npc_dota_hero_") == 2 && event.contains("with item_")) {
            String[] eventData = event.split(" ");
            HeroDO attacker = extractHeroDO(eventData[ATTACKER_NAME_INDEX], heroService);
            HeroDO victim = extractHeroDO(eventData[VICTIM_NAME_INDEX], heroService);
            ItemDO itemDO = extractItemDO(eventData[ITEM_NAME_INDEX], itemService);
            Integer damageDealt = extractDamageDealt(eventData[DAMAGE_DEALT_INDEX]);
            Long durationInMillis = timestampToDurationLong(eventData[TIMESTAMP_INDEX]);

            HeroItemDamageDO itemDamageDO = HeroItemDamageDO.builder()
                    .attacker(attacker)
                    .target(victim)
                    .item(itemDO)
                    .damage(damageDealt)
                    .timestamp(durationInMillis)
                    .match(match)
                    .build();
            damageRepository.save(itemDamageDO);

        } else if (nextProcessor != null) {
            nextProcessor.process(event, match);
        }
    }
}
