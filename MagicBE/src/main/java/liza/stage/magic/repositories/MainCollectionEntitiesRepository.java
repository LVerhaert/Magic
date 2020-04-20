package liza.stage.magic.repositories;

import liza.stage.magic.models.players.entities.MainCollectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCollectionEntitiesRepository extends PagingAndSortingRepository<MainCollectionEntity, Integer> {
}
