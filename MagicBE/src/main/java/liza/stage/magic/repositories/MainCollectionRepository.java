package liza.stage.magic.repositories;

import liza.stage.magic.models.players.entities.MainCollectionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCollectionRepository extends JpaRepository<MainCollectionEntity, Integer> {
}
