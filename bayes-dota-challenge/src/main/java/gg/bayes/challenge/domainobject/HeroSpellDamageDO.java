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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hero_spell_damage")
public class HeroSpellDamageDO extends DamageDO{

    @ManyToOne
    @JoinColumn(name = "spell_id")
    private SpellDO spell;

}
