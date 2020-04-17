package liza.stage.magic.models.magiccards.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MagicCardJson {
    @JsonProperty("id")
    private String idString;

    @JsonProperty("all_parts")
    private List<RelatedCardJson> allParts;
    @JsonProperty("arena_id")
    private long arenaId;
    private String artist;
    @JsonProperty("artist_ids")
    private List<String> artistIds;
    private Boolean booster;
    @JsonProperty("border_color")
    private String borderColor;
    @JsonProperty("card_back_id")
    private String cardBackId;
    @JsonProperty("card_faces")
    private List<CardFaceJson> cardFaces;
    private long cmc;
    @JsonProperty("collector_number")
    private String collectorNumber;
    @JsonProperty("color_identity")
    private List<String> colorIdentity;
    @JsonProperty("color_indicator")
    private List<String> colorIndicator;
    private List<String> colors;
    private Boolean digital;
    @JsonProperty("edhrec_rank")
    private long edhrecRank;
    @JsonProperty("flavor_text")
    private String flavorText;
    private Boolean foil;
    private String frame;
    @JsonProperty("frame_effects")
    private List<String> frameEffects;
    @JsonProperty("full_art")
    private Boolean fullArt;
    private List<String> games;
    @JsonProperty("hand_modifier")
    private String handModifier;
    @JsonProperty("highres_image")
    private Boolean highresImage;
    @JsonProperty("illustration_id")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUrisJson imageUris;
    private String lang;
    private String layout;
    private LegalitiesJson legalities;
    @JsonProperty("life_modifier")
    private String lifeModifier;
    private String loyalty;
    @JsonProperty("mana_cost")
    private String manaCost;
    @JsonProperty("mtgo_foil_id")
    private long mtgoFoilId;
    @JsonProperty("mtgo_id")
    private long mtgoId;
    @JsonProperty("multiverse_ids")
    private List<Long> multiverseIds;
    private String name;
    private Boolean nonfoil;
    private String object;
    @JsonProperty("oracle_id")
    private String oracleId;
    @JsonProperty("oracle_text")
    private String oracleText;
    private Boolean oversized;
    private String power;
    private PreviewJson preview;
    @JsonProperty("prints_search_uri")
    private String printsSearchUri;
    private Boolean promo;
    @JsonProperty("promo_types")
    private List<String> promoTypes;
    private String rarity;
    @JsonProperty("related_uris")
    private RelatedUrisJson relatedUris;
    @JsonProperty("released_at")
    private String releasedAt;
    private Boolean reprint;
    private Boolean reserved;
    @JsonProperty("rulings_uri")
    private String rulingsUri;
    @JsonProperty("scryfall_set_uri")
    private String scryfallSetUri;
    @JsonProperty("scryfall_uri")
    private String scryfallUri;
    private String set;
    @JsonProperty("set_name")
    private String setName;
    @JsonProperty("set_search_uri")
    private String setSearchUri;
    @JsonProperty("set_type")
    private String setType;
    @JsonProperty("set_uri")
    private String setUri;
    @JsonProperty("story_spotlight")
    private Boolean storySpotlight;
    @JsonProperty("tcgplayer_id")
    private long tcgplayerId;
    private Boolean textless;
    private String toughness;
    @JsonProperty("type_line")
    private String typeLine;
    private String uri;
    private Boolean variation;
    @JsonProperty("variation_of")
    private String variationOf;
    private String watermark;

}
