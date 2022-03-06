package gg.bayes.challenge.service.impl.processors;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroItemDO;
import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.repository.HeroItemRepository;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.HeroService;
import gg.bayes.challenge.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static gg.bayes.challenge.util.EventProcessorUtil.*;

@Service
@AllArgsConstructor
public class ItemPurchaseEventProcessor extends AbstractEventProcessor {
    private HeroService heroService;
    private ItemService itemService;
    private HeroItemRepository heroItemRepository;

    private final static int HERO_NAME_INDEX = 1;
    private final static int ITEM_NAME_INDEX = 4;
    private final static int TIMESTAMP_INDEX = 0;

    @Override
    public void process(String event, MatchDO match) {
        if (event.contains("buys")) {
            String[] eventData = event.split(" ");
            HeroDO heroDO = extractHeroDO(eventData[HERO_NAME_INDEX], heroService);
            ItemDO itemDO = extractItemDO(eventData[ITEM_NAME_INDEX], itemService);
            Long durationInMillis = timestampToDurationLong(eventData[TIMESTAMP_INDEX]);

            HeroItemDO heroItemDO = HeroItemDO.builder()
                    .hero(heroDO)
                    .item(itemDO)
                    .match(match)
                    .timestamp(durationInMillis)
                    .build();
            heroItemRepository.save(heroItemDO);
        } else if (nextProcessor != null) {
            nextProcessor.process(event, match);
        }
    }

}
