package liza.stage.magic.models.magiccards.entities;

import liza.stage.magic.models.magiccards.enums.*;
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
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    private String scryfallId;
    @Column(nullable = false)
    private Language language;
    @Column(nullable = false)
    private String oracleId;
    @Column(nullable = false)
    private String scryfallUri;
    @Column(nullable = false)
    private Rarity rarity;
    @Column(nullable = false)
    private String setName;
    @Column(nullable = false)
    private SetType setType;
    @Column(nullable = false)
    private String scryfallSetUri;
    @Column(nullable = false)
    private String setId;
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String name;

    /////////// Gameplay
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<RelatedCardEntity> relatedCards;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardFaceEntity> cardFaces;
    @Column(nullable = false)
    private long convManaCost;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colors;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIdentity;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIndicator;
    private String handModifier;
    @Column(nullable = false)
    private Layout layout;
    private String lifeModifier;
    private String loyalty;
    private String manaCost;
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;
    private String power;
    private String toughness;
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;
    @Column(nullable = false)
    private LegalitiesEntity legalities;

    ////////// Print
    private String artist;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> artistIds;
    @Column(nullable = false)
    private BorderColor borderColor;
    @Column(nullable = false)
    private String cardBackId;
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    @Column(nullable = false)
    private Frame frame;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FrameEffect> frameEffects;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<Game> games;
    private String illustrationId;
    private ImageUrisEntity imageUris;


}
