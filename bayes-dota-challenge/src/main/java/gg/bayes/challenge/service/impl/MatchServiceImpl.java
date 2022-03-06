package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroKillDTO;
import gg.bayes.challenge.exception.EntityNotFoundException;
import gg.bayes.challenge.repository.*;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.AbstractEventProcessor;
import gg.bayes.challenge.service.MatchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {

    private EventProcessorChainBuilder eventProcessorBuilder;
    private MatchRepository matchRepository;
    private HeroRepository heroRepository;
    private HeroKillRepository heroKillRepository;
    private HeroItemRepository heroItemRepository;
    private HeroSpellRepository heroSpellRepository;
    private HeroDamageRepository heroDamageRepository;
    private ModelMapper modelMapper;

    @Override
    public Long ingestMatch(String payload) {
        AbstractEventProcessor processorChain = eventProcessorBuilder.getProcessorChain();
        MatchDO match = MatchDO.builder().build();
        matchRepository.save(match);
        Scanner scanner = new Scanner(payload);
        while (scanner.hasNextLine()) {
            processorChain.process(scanner.nextLine(), match);
        }
        return match.getId();
    }

    @Override
    public List<HeroKills> getMatchSummary(Long matchId) throws EntityNotFoundException {
        MatchDO match = getMatch(matchId);
        List<HeroKillDTO> allHeroKills = heroKillRepository.getAggregatedHeroKills(match);
        List<HeroKills> mappedHeroKills = allHeroKills.stream().map(dto -> modelMapper.map(dto, HeroKills.class)).collect(Collectors.toList());
        log.info("found info for [{}] heroes for match [{}].", allHeroKills.size(), matchId);
        return mappedHeroKills;
    }


    @Override
    public List<HeroItems> getItemsForHero(Long matchId, String heroName) throws EntityNotFoundException {
        MatchDO match = getMatch(matchId);
        HeroDO hero = getHeroForName(heroName);
        List<HeroItems> itemPurchases = heroItemRepository.getItemsPurchaseDetailsForHero(match, hero).stream()
                .map(itemDetails -> modelMapper.map(itemDetails, HeroItems.class))
                .collect(Collectors.toList());
        log.info("found [{}] item purchases for hero [{}] in match [{}]", itemPurchases.size(), heroName, matchId);
        return itemPurchases;
    }

    @Override
    public List<HeroSpells> getSpellsCastForHero(Long matchId, String heroName) throws EntityNotFoundException {
        MatchDO match = getMatch(matchId);
        HeroDO hero = getHeroForName(heroName);
        List<HeroSpells> mappedHeroSpells = heroSpellRepository.getAggregatedHeroSpells(match, hero).stream()
                .map(dto -> modelMapper.map(dto, HeroSpells.class))
                .collect(Collectors.toList());
        log.info("found info for [{}] spells for hero [{}] in match [{}].", mappedHeroSpells.size(), matchId, heroName);
        return mappedHeroSpells;
    }

    @Override
    public List<HeroDamage> getDamageDoneByHero(Long matchId, String heroName) throws EntityNotFoundException {
        MatchDO match = getMatch(matchId);
        HeroDO attacker = getHeroForName(heroName);

        List<HeroDamage> targetToCompleteHeroDamage = heroDamageRepository.getAggregatedHeroDamage(match, attacker).stream()
                .map(dto-> modelMapper.map(dto, HeroDamage.class))
                .collect(Collectors.toList());

        log.info("found damage info against [{}] heroes by hero [{}] for match [{}]", targetToCompleteHeroDamage.size(), heroName, matchId);
        return targetToCompleteHeroDamage;
    }

    private MatchDO getMatch(Long matchId) throws EntityNotFoundException {
        Optional<MatchDO> possibleMatch = matchRepository.findById(matchId);
        return possibleMatch.orElseThrow(() -> new EntityNotFoundException("match id not found"));
    }

    private HeroDO getHeroForName(String heroName) throws EntityNotFoundException {
        Optional<HeroDO> possibleMatch = heroRepository.findByName(heroName);
        return possibleMatch.orElseThrow(() -> new EntityNotFoundException("hero not found for name"));
    }
}
