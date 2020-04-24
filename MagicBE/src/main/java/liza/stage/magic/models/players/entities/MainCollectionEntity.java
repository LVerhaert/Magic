package liza.stage.magic.models.players.entities;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "maincollection")
@Table(name = "maincollection")
public class MainCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magiccard_maincollection",
            joinColumns = @JoinColumn(name = "maincollection_id"),
            inverseJoinColumns = @JoinColumn(name = "magiccard_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MagicCardEntity> magicCards;


}
