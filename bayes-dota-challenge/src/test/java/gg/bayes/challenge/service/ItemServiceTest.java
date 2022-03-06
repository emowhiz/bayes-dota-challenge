package gg.bayes.challenge.service;

import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.repository.ItemRepository;
import gg.bayes.challenge.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;
    private ItemService itemService;

    @Before
    public void setup() {
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    public void ItemServiceShouldReturnAnItemForName(){
        ItemDO itemDO = ItemDO.builder().name("clarity").build();

        given(itemRepository.findByName("clarity")).willReturn(Optional.of(itemDO));

        ItemDO item = itemService.getOrInsertAndGet("clarity");
        assertThat(item.getName()).isEqualTo(itemDO.getName());
    }

}
