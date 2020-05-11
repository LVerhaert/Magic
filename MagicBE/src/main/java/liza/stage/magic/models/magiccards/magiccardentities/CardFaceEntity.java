package liza.stage.magic.models.magiccards.magiccardentities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity(name = "cardface")
@Table(name = "cardface")
public class CardFaceEntity {
    /////////// Core
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;

//    @Type(type = "org.hibernate.type.TextType")
//    private String flavorText;

    /////////// Gameplay
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Color> colors;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Color> colorIndicator;
//    @Type(type = "org.hibernate.type.TextType")
//    private String loyalty;
//    private String manaCost;
//    private String power;
//    private String toughness;

    ////////// Print
    private ImageUrisEntity imageUris;

//    private String artist;
//    private String artistId;
//    private String illustrationId;
}
