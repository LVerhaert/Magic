package liza.stage.magic.repositories.players;


import liza.stage.magic.models.players.entities.PlayerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerEntitiesRepository extends PagingAndSortingRepository<PlayerEntity, Integer> {
}
