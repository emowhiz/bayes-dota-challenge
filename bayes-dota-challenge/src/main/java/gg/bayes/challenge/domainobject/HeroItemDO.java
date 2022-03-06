package gg.bayes.challenge.domainobject;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hero_item")
public class HeroItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private HeroDO hero;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemDO item;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchDO match;

    @Column(nullable = false)
    private Long timestamp;

}
