package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.domainobject.ItemDO;
import gg.bayes.challenge.repository.ItemRepository;
import gg.bayes.challenge.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Override
    public ItemDO getOrInsertAndGet(String hero) {
        return itemRepository.findByName(hero).orElseGet(() -> itemRepository.save(ItemDO.builder().name(hero).build()));
    }
}
