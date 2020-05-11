package liza.stage.magic.models.players.playerentities;

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

    @OneToOne(mappedBy = "player")
    private MainCollectionEntity mainCollection;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DeckEntity> decks;

}
