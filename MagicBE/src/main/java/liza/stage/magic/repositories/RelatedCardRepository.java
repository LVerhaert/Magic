package liza.stage.magic.repositories;

import liza.stage.magic.models.magiccards.entities.RelatedCardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedCardRepository extends PagingAndSortingRepository<RelatedCardEntity, Long> {
    List<RelatedCardEntity> findAllByScryfallId(String scryfallId);
}
