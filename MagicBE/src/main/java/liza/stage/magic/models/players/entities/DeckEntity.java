package liza.stage.magic.models.players.entities;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "deck")
@Table(name = "deck")
public class DeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magiccard_deck",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "magiccard_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MagicCardEntity> magicCards;

    private String deckName;

}
