package liza.stage.magic.models.players.playerentities;

import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @OneToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private PlayerEntity player;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magiccard_maincollection",
            joinColumns = @JoinColumn(name = "maincollection_id"),
            inverseJoinColumns = @JoinColumn(name = "magiccard_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MagicCardEntity> magicCards;


}
