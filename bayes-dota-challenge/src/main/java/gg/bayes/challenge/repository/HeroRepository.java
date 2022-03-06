package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.HeroDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroRepository extends JpaRepository<HeroDO, Integer> {
    Optional<HeroDO> findByName(String name);
}
