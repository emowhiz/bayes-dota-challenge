package gg.bayes.challenge.repository;

import gg.bayes.challenge.domainobject.MatchDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDO, Long> {
}
