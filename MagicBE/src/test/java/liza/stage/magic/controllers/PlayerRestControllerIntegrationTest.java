package liza.stage.magic.controllers;

import liza.stage.magic.Main;
import liza.stage.magic.models.players.playerentities.PlayerEntity;
import liza.stage.magic.repositories.playerrepositories.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PlayerRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void givenPlayers_whenGetPlayers_thenStatus200()
            throws Exception {
        createTestPlayers();

        mvc.perform(get("/players").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("data[0].name", is("Liza")))
                .andExpect(jsonPath("data[1].name", is("Stefan")));
    }

    private void createTestPlayers() {
        PlayerEntity playerLiza = new PlayerEntity();
        playerLiza.setName("Liza");

        playerRepository.saveAndFlush(playerLiza);


        PlayerEntity playerStefan = new PlayerEntity();
        playerStefan.setName("Stefan");

        playerRepository.saveAndFlush(playerStefan);
    }
}
