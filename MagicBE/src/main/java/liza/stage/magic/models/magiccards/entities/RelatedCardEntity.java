package liza.stage.magic.models.magiccards.entities;

import liza.stage.magic.models.magiccards.enums.Relationship;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "relatedcard")
@Table(name = "relatedcard")
public class RelatedCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String scryfallId;
    @Column(nullable = false)
    private Relationship relationship;
}
