package gg.bayes.challenge.service;

import gg.bayes.challenge.domainobject.MatchDO;
import lombok.Setter;

@Setter
public abstract class AbstractEventProcessor {
    public AbstractEventProcessor nextProcessor;

    public abstract void process(String event, MatchDO match);
}
