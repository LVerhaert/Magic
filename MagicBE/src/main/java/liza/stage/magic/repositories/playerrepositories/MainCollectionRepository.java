package liza.stage.magic.repositories.playerrepositories;

import liza.stage.magic.models.players.playerentities.MainCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCollectionRepository extends JpaRepository<MainCollectionEntity, Integer> {
}
