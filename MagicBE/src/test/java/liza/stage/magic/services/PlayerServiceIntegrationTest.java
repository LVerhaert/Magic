package liza.stage.magic.services;

import liza.stage.magic.models.players.playerdtos.PlayerDto;
import liza.stage.magic.models.players.playerentities.PlayerEntity;
import liza.stage.magic.repositories.playerrepositories.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ComponentScan("liza.stage.magic.*")
@DataJpaTest
public class PlayerServiceIntegrationTest {

    @Autowired
    private PlayerService playerService;
    @MockBean
    private PlayerRepository playerRepository;

    @Before
    public void setUp() {
        PlayerEntity playerLiza = new PlayerEntity();
        playerLiza.setName("Liza");
        playerLiza.setId(1);

        Mockito.when(playerRepository.findById(playerLiza.getId())).thenReturn(java.util.Optional.of(playerLiza));
    }

    @Test
    public void whenValidName_thenPlayerShouldBeFound() {
        PlayerDto found = playerService.findPlayer(1);

        assertThat(found.getName()).isEqualTo("Liza");
    }

    @TestConfiguration
    static class PlayerServiceTestContextConfiguration {

        @Bean
        public PlayerService playerService() {
            return new PlayerService();
        }
    }
}
