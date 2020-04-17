package liza.stage.magic.models.users.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity(name = "deck")
@Table(schema = "personal", name = "deck")
public class DeckEntity extends CollectionEntity {
    private String deckName;

}
