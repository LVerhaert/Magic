package liza.stage.magic.repositories;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagicCardRepository extends JpaRepository<MagicCardEntity, String> {
    List<MagicCardEntity> findTop10ByNameContains(String nameContains);

    List<MagicCardEntity> findAllByScryfallIdIn(List<String> ids, Pageable pageable);
}
