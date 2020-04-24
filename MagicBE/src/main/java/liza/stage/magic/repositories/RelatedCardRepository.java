package liza.stage.magic.repositories;

import liza.stage.magic.models.magiccards.entities.RelatedCardEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedCardRepository extends JpaRepository<RelatedCardEntity, Long> {
    RelatedCardEntity findByScryfallId(String scryfallId);
}
