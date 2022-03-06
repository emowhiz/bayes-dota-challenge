package gg.bayes.challenge.util;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.domainobject.SpellDO;
import gg.bayes.challenge.service.HeroService;
import gg.bayes.challenge.service.ItemService;
import gg.bayes.challenge.service.SpellService;

import java.time.Duration;
import java.time.LocalTime;

public class EventProcessorUtil {
    public static Long timestampToDurationLong(String s) {
        LocalTime lt = LocalTime.parse(s.replaceAll("\\[", "").replaceAll("]", ""));
        Duration duration = Duration.between(LocalTime.MIN, lt);

        return duration.toMillis();
    }

    public static ItemDO extractItemDO(String eventDatum, ItemService itemService) {
        String itemName = eventDatum.replaceAll("item_", "");
        return itemService.getOrInsertAndGet(itemName);
    }

    public static SpellDO extractSpellDO(String eventDatum,  SpellService spellService) {
        return spellService.getOrInsertAndGet(eventDatum);
    }

    public static Integer extractSpellLevel(String eventDatum) {
        String level = eventDatum.replaceAll("\\)", "");
        return Integer.parseInt(level);
    }

    public static Integer extractDamageDealt(String eventDatum) {
        return Integer.parseInt(eventDatum);
    }

    public static HeroDO extractHeroDO(String eventDatum, HeroService heroService) {
        String heroName = eventDatum.replaceAll("npc_dota_hero_", "");
        return heroService.getOrInsertAndGet(heroName);
    }
}
