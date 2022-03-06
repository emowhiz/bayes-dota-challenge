package gg.bayes.challenge.domainobject;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hero_kill")
public class HeroKillDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "killer_id", nullable = false)
    private HeroDO killer;

    @ManyToOne
    @JoinColumn(name = "victim_id", nullable = false)
    private HeroDO victim;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchDO match;

    @Column(nullable = false)
    private Long timestamp;

}
