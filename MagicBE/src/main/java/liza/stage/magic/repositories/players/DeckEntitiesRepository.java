package liza.stage.magic.repositories.players;

import liza.stage.magic.models.players.entities.DeckEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckEntitiesRepository extends PagingAndSortingRepository<DeckEntity, Integer> {
}
