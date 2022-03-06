package gg.bayes.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DotaChallengeApplicationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ingestMatchDataIT() {
        String matchData = "[00:08:43.460] npc_dota_hero_pangolier casts ability pangolier_swashbuckle (lvl 1) on dota_unknown\n" +
                "[00:08:46.693] npc_dota_hero_snapfire buys item item_clarity\n" +
                "[00:08:46.759] npc_dota_hero_dragon_knight buys item item_quelling_blade\n" +
                "[00:08:46.992] npc_dota_hero_dragon_knight buys item item_gauntlets";

        ResponseEntity<Long> ingestMatchResponse = restTemplate.postForEntity("/api/match", matchData, Long.class);
        assertThat(ingestMatchResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(ingestMatchResponse.getBody())).isEqualTo(1L);
    }
}
