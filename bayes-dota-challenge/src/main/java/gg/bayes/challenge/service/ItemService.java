package gg.bayes.challenge.service;

import gg.bayes.challenge.domainobject.ItemDO;

public interface ItemService {
    ItemDO getOrInsertAndGet(String item);
}
