package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.impl.processors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class EventProcessorChainBuilder {

    private ItemPurchaseEventProcessor itemPurchaseEventProcessor;
    private HeroKillEventProcessor heroKillEventProcessor;
    private SpellCastEventProcessor spellCastEventProcessor;
    private NormalDamageEventProcessor normalDamageEventProcessor;
    private SpellDamageEventProcessor spellDamageEventProcessor;
    private ItemDamageEventProcessor itemDamageEventProcessor;

    @PostConstruct
    public void init() {
        itemPurchaseEventProcessor.setNextProcessor(heroKillEventProcessor);
        heroKillEventProcessor.setNextProcessor(spellCastEventProcessor);
        spellCastEventProcessor.setNextProcessor(normalDamageEventProcessor);
        normalDamageEventProcessor.setNextProcessor(spellDamageEventProcessor);
        spellDamageEventProcessor.setNextProcessor(itemDamageEventProcessor);
    }

    public AbstractEventProcessor getProcessorChain() {
        return itemPurchaseEventProcessor;
    }
}
