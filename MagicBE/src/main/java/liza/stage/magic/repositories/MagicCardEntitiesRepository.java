package liza.stage.magic.repositories;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagicCardEntitiesRepository extends PagingAndSortingRepository<MagicCardEntity, String> {
    List<MagicCardEntity> findTop10ByNameContains(String nameContains);

    List<MagicCardEntity> findAllByScryfallIdIn(List<String> ids, Pageable pageable);
}
