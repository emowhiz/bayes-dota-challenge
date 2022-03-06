package gg.bayes.challenge.controller;

import gg.bayes.challenge.config.BeanConfig;
import gg.bayes.challenge.rest.controller.MatchController;
import gg.bayes.challenge.service.MatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MatchController.class, BeanConfig.class})
public class MatchControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MatchService matchService;

    @Test
    public void ingest() throws Exception {
        String matchData = "[00:08:43.460] npc_dota_hero_pangolier casts ability pangolier_swashbuckle (lvl 1) on dota_unknown\n" +
                "[00:08:46.693] npc_dota_hero_snapfire buys item item_clarity\n" +
                "[00:08:46.759] npc_dota_hero_dragon_knight buys item item_quelling_blade\n" +
                "[00:08:46.992] npc_dota_hero_dragon_knight buys item item_gauntlets";

        given(matchService.ingestMatch(matchData)).willReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/match")
                        .content(matchData)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("1"));
    }
}
