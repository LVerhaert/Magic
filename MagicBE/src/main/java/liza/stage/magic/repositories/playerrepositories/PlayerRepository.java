package liza.stage.magic.repositories.playerrepositories;


import liza.stage.magic.models.players.playerentities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
