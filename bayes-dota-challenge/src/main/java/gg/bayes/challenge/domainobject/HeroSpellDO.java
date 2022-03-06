package gg.bayes.challenge.domainobject;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hero_spell")
public class HeroSpellDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private HeroDO hero;

    @ManyToOne
    @JoinColumn(name = "spell_id", nullable = false)
    private SpellDO spell;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchDO match;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Long timestamp;

}
