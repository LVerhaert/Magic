
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MagicCard {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    @Type(type = "org.hibernate.type.TextType")
    private String idString;

    @JsonProperty("all_parts")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magic_card_related_card",
    joinColumns = {@JoinColumn(name="magic_card_id")},
    inverseJoinColumns = {@JoinColumn(name = "related_card_id")})
    private List<RelatedCard> allParts;
    @JsonProperty("arena_id")
    private long arenaId;
    @Type(type = "org.hibernate.type.TextType")
    private String artist;
    @JsonProperty("artist_ids")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> artistIds;
    private Boolean booster;
    @JsonProperty("border_color")
    private BorderColor borderColor;
    @JsonProperty("card_back_id")
    @Type(type = "org.hibernate.type.TextType")
    private String cardBackId;
    @JsonProperty("card_faces")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="magic_card_card_face")
    private List<CardFace> cardFaces;
    private long cmc;
    @JsonProperty("collector_number")
    @Type(type = "org.hibernate.type.TextType")
    private String collectorNumber;
    @JsonProperty("color_identity")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIdentity;
    @JsonProperty("color_indicator")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIndicator;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colors;
    private Boolean digital;
    @JsonProperty("edhrec_rank")
    private long edhrecRank;
    @JsonProperty("flavor_text")
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    private Boolean foil;
    private Frame frame;
    @JsonProperty("frame_effects")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FrameEffect> frameEffects;
    @JsonProperty("full_art")
    private Boolean fullArt;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> games;
    @JsonProperty("hand_modifier")
    @Type(type = "org.hibernate.type.TextType")
    private String handModifier;
    @JsonProperty("highres_image")
    private Boolean highresImage;
    @JsonProperty("illustration_id")
    @Type(type = "org.hibernate.type.TextType")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUris imageUris;
    private Language lang;
    private Layout layout;
    private Legalities legalities;
    @JsonProperty("life_modifier")
    @Type(type = "org.hibernate.type.TextType")
    private String lifeModifier;
    @Type(type = "org.hibernate.type.TextType")
    private String loyalty;
    @JsonProperty("mana_cost")
    @Type(type = "org.hibernate.type.TextType")
    private String manaCost;
    @JsonProperty("mtgo_foil_id")
    private long mtgoFoilId;
    @JsonProperty("mtgo_id")
    private long mtgoId;
    @JsonProperty("multiverse_ids")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Long> multiverseIds;
    @Type(type = "org.hibernate.type.TextType")
    private String name;
    private Boolean nonfoil;
    private ObjectType object;
    @JsonProperty("oracle_id")
    @Type(type = "org.hibernate.type.TextType")
    private String oracleId;
    @JsonProperty("oracle_text")
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;
    private Boolean oversized;
    @Type(type = "org.hibernate.type.TextType")
    private String power;
    private Preview preview;
    @JsonProperty("prints_search_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String printsSearchUri;
    private Boolean promo;
    @JsonProperty("promo_types")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> promoTypes;
    private Rarity rarity;
    @JsonProperty("related_uris")
    private RelatedUris relatedUris;
    @JsonProperty("released_at")
    @Type(type = "org.hibernate.type.TextType")
    private String releasedAt;
    private Boolean reprint;
    private Boolean reserved;
    @JsonProperty("rulings_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String rulingsUri;
    @JsonProperty("scryfall_set_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String scryfallSetUri;
    @JsonProperty("scryfall_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String scryfallUri;
    @Type(type = "org.hibernate.type.TextType")
    private String set;
    @JsonProperty("set_name")
    @Type(type = "org.hibernate.type.TextType")
    private String setName;
    @JsonProperty("set_search_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String setSearchUri;
    @JsonProperty("set_type")
    private SetType setType;
    @JsonProperty("set_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String setUri;
    @JsonProperty("story_spotlight")
    private Boolean storySpotlight;
    @JsonProperty("tcgplayer_id")
    private long tcgplayerId;
    private Boolean textless;
    @Type(type = "org.hibernate.type.TextType")
    private String toughness;
    @JsonProperty("type_line")
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;
    @Type(type = "org.hibernate.type.TextType")
    private String uri;
    private Boolean variation;
    @JsonProperty("variation_of")
    @Type(type = "org.hibernate.type.TextType")
    private String variationOf;
    @Type(type = "org.hibernate.type.TextType")
    private String watermark;

}
