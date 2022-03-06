package gg.bayes.challenge.rest.controller;

import gg.bayes.challenge.exception.EntityNotFoundException;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;


    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes = "text/plain")
    public ResponseEntity<Long> ingestMatch(@RequestBody @NotNull @NotBlank String payload) {
        final Long matchId = matchService.ingestMatch(payload);
        return ResponseEntity.ok(matchId);
    }

    @GetMapping("{matchId}")
    public ResponseEntity<List<HeroKills>> getMatch(@PathVariable("matchId") Long matchId) throws EntityNotFoundException {
        log.info("finding kill summary for match by id [{}]", matchId);
        List<HeroKills> matchSummary = matchService.getMatchSummary(matchId);
        return ResponseEntity.ok(matchSummary);
    }

    @GetMapping("{matchId}/{heroName}/items")
    public ResponseEntity<List<HeroItems>> getItems(@PathVariable("matchId") Long matchId,
                                                    @PathVariable("heroName") String heroName) throws EntityNotFoundException {
        log.info("finding item purchases for match by id [{}] and hero [{}]", matchId, heroName);
        List<HeroItems> matchSummary = matchService.getItemsForHero(matchId, heroName);
        return ResponseEntity.ok(matchSummary);
    }

    @GetMapping("{matchId}/{heroName}/spells")
    public ResponseEntity<List<HeroSpells>> getSpells(@PathVariable("matchId") Long matchId,
                                                      @PathVariable("heroName") String heroName) throws EntityNotFoundException {
        log.info("finding spell details in match by id [{}] and hero [{}]", matchId, heroName);
        List<HeroSpells> matchSummary = matchService.getSpellsCastForHero(matchId, heroName);
        return ResponseEntity.ok(matchSummary);
    }

    @GetMapping("{matchId}/{heroName}/damage")
    public ResponseEntity<List<HeroDamage>> getDamage(@PathVariable("matchId") Long matchId,
                                                      @PathVariable("heroName") String heroName) throws EntityNotFoundException {
        log.info("finding damage done to enemy heroes in match by id [{}] and hero [{}]", matchId, heroName);
        List<HeroDamage> matchSummary = matchService.getDamageDoneByHero(matchId, heroName);
        return ResponseEntity.ok(matchSummary);
    }
}
