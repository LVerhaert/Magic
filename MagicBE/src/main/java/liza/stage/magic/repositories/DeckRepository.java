package liza.stage.magic.repositories;

import liza.stage.magic.models.players.entities.DeckEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Integer> {
    List<DeckEntity> findDeckEntitiesByDeckName(String deckName);

    List<DeckEntity> findDeckEntitiesByDeckName(String deckName, Pageable pageable);
}
