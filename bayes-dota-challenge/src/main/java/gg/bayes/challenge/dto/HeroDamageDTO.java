package gg.bayes.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroDamageDTO {
    private String target;
    private Long damageInstances;
    private Long totalDamage;
}
