package gg.bayes.challenge.domainobject;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match")
public class MatchDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
