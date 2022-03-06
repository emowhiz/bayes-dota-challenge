package gg.bayes.challenge.domainobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DamageDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchDO match;

    @ManyToOne
    @JoinColumn(name = "attacker_id", nullable = false)
    private HeroDO attacker;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private HeroDO target;

    @Column(nullable = false)
    private Long timestamp;

    @Column(nullable = false)
    private Integer damage;


}
