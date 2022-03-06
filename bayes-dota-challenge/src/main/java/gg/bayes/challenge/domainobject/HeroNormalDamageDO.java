package gg.bayes.challenge.domainobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@Table(name = "hero_normal_damage")
public class HeroNormalDamageDO extends DamageDO{

}
