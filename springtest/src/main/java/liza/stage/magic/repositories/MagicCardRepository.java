package liza.stage.magic.repositories;

import liza.stage.magic.domain.MagicCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardRepository extends JpaRepository<MagicCard,Long> {
}
