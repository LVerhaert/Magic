package liza.stage.magic.repositories;

import liza.stage.magic.models.entities.MagicCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardEntitiesRepository extends JpaRepository<MagicCardEntity, String> {
}
