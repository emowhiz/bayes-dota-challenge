package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroItemDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroItemRepository extends JpaRepository<HeroItemDO, Integer> {

    @Query(value = "select new gg.bayes.challenge.dto.HeroItemDTO(i.item.name as item, i.timestamp) from HeroItemDO i " +
            "where i.match=:match and i.hero=:hero")
    List<HeroItemDTO> getItemsPurchaseDetailsForHero(MatchDO match, HeroDO hero);
}
