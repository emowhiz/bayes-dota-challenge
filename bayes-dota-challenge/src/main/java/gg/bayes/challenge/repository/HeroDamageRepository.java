package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.DamageDO;
import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroDamageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroDamageRepository extends JpaRepository<DamageDO, Integer> {
    @Query(value = "select new gg.bayes.challenge.dto.HeroDamageDTO(d.target.name as target, count(*) as damageInstances, sum(damage) as totalDamage) from DamageDO d " +
            "where d.match=:match  and d.attacker=:attacker group by d.target.name")
    List<HeroDamageDTO> getAggregatedHeroDamage(MatchDO match, HeroDO attacker);

}
