package gg.bayes.challenge.service;

import gg.bayes.challenge.exception.EntityNotFoundException;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

import java.util.List;

public interface MatchService {
    Long ingestMatch(String payload);

    List<HeroKills> getMatchSummary(Long matchId) throws EntityNotFoundException;

    List<HeroItems> getItemsForHero(Long matchId, String heroName) throws EntityNotFoundException;

    List<HeroSpells> getSpellsCastForHero(Long matchId, String heroName) throws EntityNotFoundException;

    List<HeroDamage> getDamageDoneByHero(Long matchId, String heroName) throws EntityNotFoundException;

}
