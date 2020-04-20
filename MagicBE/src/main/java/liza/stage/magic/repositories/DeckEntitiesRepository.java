package liza.stage.magic.repositories;

import liza.stage.magic.models.players.entities.DeckEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckEntitiesRepository extends PagingAndSortingRepository<DeckEntity, Integer> {
    List<DeckEntity> findDeckEntitiesByDeckName(String deckName);

    List<DeckEntity> findDeckEntitiesByDeckName(String deckName, Pageable pageable);
}
