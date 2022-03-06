package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.SpellDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpellRepository extends JpaRepository<SpellDO, Integer> {
    Optional<SpellDO> findByName(String name);
}
