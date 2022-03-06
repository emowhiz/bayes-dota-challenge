package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroSpellDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroSpellsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroSpellRepository extends JpaRepository<HeroSpellDO, Integer> {
    @Query(value = "select new gg.bayes.challenge.dto.HeroSpellsDTO(hs.spell.name as spell, count(*) as casts) " +
            "from HeroSpellDO hs where hs.match=:match and hs.hero=:hero group by hs.spell.name")
    List<HeroSpellsDTO> getAggregatedHeroSpells(MatchDO match, HeroDO hero);

}
