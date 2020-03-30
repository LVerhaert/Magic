package liza.stage.magic.models.entities;

import liza.stage.magic.models.enums.Relationship;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RelatedCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String scryfallId;
    @Column(nullable = false)
    private Relationship relationship;
}
