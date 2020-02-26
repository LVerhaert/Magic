package liza.stage.springtest.repositories;

import liza.stage.springtest.model.MagicCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardRepository extends JpaRepository<MagicCard,Long> {
}
