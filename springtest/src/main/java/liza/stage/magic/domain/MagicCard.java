
package liza.stage.magic.domain;

import liza.stage.magic.domain.enums.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class MagicCard {
    /////////// Core
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String scryfallId;
    private Language language;
    private String oracleId;
    private String scryfallUri;
    private Rarity rarity;
    private String setName;
    private SetType setType;
    private String scryfallSetUri;
    private String setId;
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    /////////// Gameplay
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magic_card_related_card",
            joinColumns = {@JoinColumn(name = "magic_card_id")},
            inverseJoinColumns = {@JoinColumn(name = "related_card_id")})
    private List<RelatedCard> relatedCards;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardFace> cardFaces;
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
    private Legalities legalities;

    ////////// Print
    private String artist;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> artistIds;
    private BorderColor borderColor;
    private String cardBackId;
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    private Frame frame;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FrameEffect> frameEffects;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> games;
    private String illustrationId;
    private ImageUris imageUris;


}
