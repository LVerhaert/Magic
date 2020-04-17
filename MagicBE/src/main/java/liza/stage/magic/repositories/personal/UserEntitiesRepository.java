package liza.stage.magic.repositories.personal;

import liza.stage.magic.models.magiccards.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntitiesRepository extends PagingAndSortingRepository<UserEntity, Integer> {
}
