package liza.stage.magic.models.users.entities;

import liza.stage.magic.models.users.entities.magiccards.MagicCardEntity;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "collection")
@Table(schema = "personal", name = "collection")
public abstract class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MagicCardEntity> magicCards;
}
