package liza.stage.magic.repositories.playerrepositories;

import liza.stage.magic.models.players.playerentities.DeckEntity;
import liza.stage.magic.models.players.playerentities.PlayerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Integer> {
    List<DeckEntity> findByPlayer(PlayerEntity player, Pageable pageable);
}
