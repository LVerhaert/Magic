package liza.stage.magic.models.users.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "user")
@Table(schema = "personal", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private MainCollectionEntity mainCollection;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DeckEntity> decks;

}
