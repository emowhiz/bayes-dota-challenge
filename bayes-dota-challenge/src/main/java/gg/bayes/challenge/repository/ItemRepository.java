package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.ItemDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemDO, Integer> {
    Optional<ItemDO> findByName(String name);
}
