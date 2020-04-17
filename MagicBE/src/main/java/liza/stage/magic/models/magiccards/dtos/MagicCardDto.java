package liza.stage.magic.models.magiccards.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MagicCardDto {
    /////////// Core
    private String scryfallId;
    private Language language;
    private String oracleId;
    private String scryfallUri;
    private Rarity rarity;
    private String setName;
    private SetType setType;
    private String scryfallSetUri;
    private String setId;
    private String name;

    /////////// Gameplay
    private Map<String, Relationship> relatedCards;
    private List<CardFaceDto> cardFaces;
    private long convManaCost;
    private List<Color> colors;
    private List<Color> colorIdentity;
    private List<Color> colorIndicator;
    private String handModifier;
    private Layout layout;
    private String lifeModifier;
    private String loyalty;
    private String manaCost;
    private String oracleText;
    private String power;
    private String toughness;
    private String typeLine;
    private LegalitiesDto legalities;

    ////////// Print
    private String artist;
    private List<String> artistIds;
    private BorderColor borderColor;
    private String cardBackId;
    private String flavorText;
    private Frame frame;
    private List<FrameEffect> frameEffects;
    private List<Game> games;
    private String illustrationId;
    private String smallImageUri;
    private String largeImageUri;


}
