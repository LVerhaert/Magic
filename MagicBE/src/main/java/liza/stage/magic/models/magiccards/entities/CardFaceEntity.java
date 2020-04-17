package liza.stage.magic.models.magiccards.entities;

import liza.stage.magic.models.magiccards.enums.Color;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "cardface")
@Table(name = "cardface")
public class CardFaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String artist;
    private String artistId;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIndicator;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colors;
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    private String illustrationId;
    private ImageUrisEntity imageUris;
    @Type(type = "org.hibernate.type.TextType")
    private String loyalty;
    private String manaCost;
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String name;
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;
    private String power;
    private String toughness;
    @Type(type = "org.hibernate.type.TextType")
//    @Column(nullable = false)
    private String typeLine;

}
