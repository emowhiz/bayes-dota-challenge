package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.HeroDO;
import gg.bayes.challenge.domainobject.HeroItemDO;
import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.domainobject.MatchDO;
import gg.bayes.challenge.dto.HeroItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroItemRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    HeroItemRepository heroItemRepository;

    @Test
    public void shouldReturnHeroItemDTOForASpecificMatchAndHero(){
        MatchDO matchDO = MatchDO.builder().build();
        HeroDO heroDO = HeroDO.builder().name("pangolier").build();
        HeroDO heroDO2 = HeroDO.builder().name("axe").build();
        ItemDO itemDO = ItemDO.builder().name("clarity").build();
        HeroItemDO heroItemDO1 = HeroItemDO.builder().hero(heroDO).item(itemDO).match(matchDO).timestamp(10001L).build();
        HeroItemDO heroItemDO2 = HeroItemDO.builder().hero(heroDO2).item(itemDO).match(matchDO).timestamp(10000L).build();

        testEntityManager.persistAndFlush(matchDO);
        testEntityManager.persistAndFlush(heroDO);
        testEntityManager.persistAndFlush(heroDO2);
        testEntityManager.persistAndFlush(itemDO);
        testEntityManager.persistAndFlush(heroItemDO1);
        testEntityManager.persistAndFlush(heroItemDO2);

        List<HeroItemDTO> itemDetails = heroItemRepository.getItemsPurchaseDetailsForHero(matchDO, heroDO);
        assertThat(itemDetails.size()).isEqualTo(1);
        assertThat(itemDetails.get(0).getTimestamp()).isEqualTo(10001L);

    }
}
