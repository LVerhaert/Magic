package liza.stage.magic.repositories.personal;

import liza.stage.magic.models.magiccards.entities.personal.CollectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionEntitiesRepository extends PagingAndSortingRepository<CollectionEntity, Integer> {
}
