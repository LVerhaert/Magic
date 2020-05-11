package liza.stage.magic.models.magiccards.magiccardentities;

import liza.stage.magic.models.magiccards.magiccardenums.Game;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "magiccard")
@Table(name = "magiccard")
public class MagicCardEntity {
    /////////// Core
    @Id
    private String scryfallId;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;

//    @Type(type = "org.hibernate.type.TextType")
//    private String flavorText;
//    @Column(nullable = false)
//    private Language language;
//    @Column(nullable = false)
//    private String oracleId;
//    @Column(nullable = false)
//    private String scryfallUri;


    /////////// Gameplay
//    @Column(nullable = false)
//    private long convManaCost;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Color> colors;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Color> colorIdentity;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Color> colorIndicator;
//    private String handModifier;
//    private String lifeModifier;
//    private String loyalty;
//    private String manaCost;
//    private String power;
//    private String toughness;


    ////////// Print
    private ImageUrisEntity imageUris;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardFaceEntity> cardFaces;

//    @Column(nullable = false)
//    private Layout layout;
//    private String artist;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<String> artistIds;
//    @Column(nullable = false)
//    private BorderColor borderColor;
//    @Column(nullable = false)
//    private String cardBackId;
//    @Column(nullable = false)
//    private Frame frame;
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<FrameEffect> frameEffects;
//    private String illustrationId;

    ///////// Extra
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<Game> games;
    @Column(nullable = false)
    private LegalitiesEntity legalities;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<RelatedCardEntity> relatedCards;
//    @Column(nullable = false)
//    private Rarity rarity;
//    @Column(nullable = false)
//    private String setName;
//    @Column(nullable = false)
//    private SetType setType;
//    @Column(nullable = false)
//    private String scryfallSetUri;
//    @Column(nullable = false)
//    private String setId;


}
