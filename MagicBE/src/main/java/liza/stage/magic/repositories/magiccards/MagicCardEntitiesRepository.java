package liza.stage.magic.repositories.magiccards;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardEntitiesRepository extends PagingAndSortingRepository<MagicCardEntity, String> {

}
