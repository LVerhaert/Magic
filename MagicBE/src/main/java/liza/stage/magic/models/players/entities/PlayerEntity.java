package liza.stage.magic.models.players.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "player")
@Table(name = "player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "maincollection_id")
    private MainCollectionEntity mainCollection;

    @OneToMany//(cascade = CascadeType.ALL)
    @JoinTable(name = "deck_player",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "deck_id"))
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DeckEntity> decks;

}
