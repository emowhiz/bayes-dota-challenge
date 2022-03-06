package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.HeroKillDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroKillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroKillRepository extends JpaRepository<HeroKillDO, Integer> {

    @Query(value = "select new gg.bayes.challenge.dto.HeroKillDTO(hk.killer.name as hero, count(*) as kills) from HeroKillDO hk " +
            "where hk.match=:match group by hk.killer.name")
    List<HeroKillDTO> getAggregatedHeroKills(MatchDO match);
}
