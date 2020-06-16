package liza.stage.magic.repositories.playerrepositories;

import liza.stage.magic.models.players.playerentities.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void givenPlayer_whenSave_thenGetOk() {
        PlayerEntity playerLiza = new PlayerEntity();
        playerLiza.setName("Liza");

        entityManager.persist(playerLiza);
        entityManager.flush();


        PlayerEntity player2 = playerRepository.findById(playerLiza.getId()).orElse(null);
        assertThat(playerLiza.getName()).isEqualTo(player2.getName());
    }
}
