package gg.bayes.challenge.domainobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hero_item_damage")
public class HeroItemDamageDO extends DamageDO{

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemDO item;

}
