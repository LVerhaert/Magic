package liza.stage.magic.models.users.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity(name = "maincollection")
@Table(name = "maincollection", schema = "personal")
public class MainCollectionEntity extends CollectionEntity {
}
