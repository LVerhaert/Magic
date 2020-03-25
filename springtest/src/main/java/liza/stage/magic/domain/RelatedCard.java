
package liza.stage.magic.domain;

import liza.stage.magic.domain.enums.Relationship;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class RelatedCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String scryfallId;
    private Relationship relationship;
    @Type(type = "org.hibernate.type.TextType")
    private String name;
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;

}
