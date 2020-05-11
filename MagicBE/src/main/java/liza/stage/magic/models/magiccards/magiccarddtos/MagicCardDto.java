package liza.stage.magic.models.magiccards.magiccarddtos;

import liza.stage.magic.models.magiccards.magiccardenums.Game;
import lombok.Data;

import java.util.List;

@Data
public class MagicCardDto {
    /////////// Core
    private String scryfallId;
    private String name;
    private String typeLine;
    private String oracleText;
//    private String flavorText;
//    private Language language;
//    private String oracleId;
//    private String scryfallUri;

    /////////// Gameplay
//    private long convManaCost;
//    private List<Color> colors;
//    private List<Color> colorIdentity;
//    private List<Color> colorIndicator;
//    private String handModifier;
//    private String lifeModifier;
//    private String loyalty;
//    private String manaCost;
//    private String power;
//    private String toughness;

    ////////// Print
    private String smallImageUri;
    private String largeImageUri;
    private List<CardFaceDto> cardFaces;
//    private Layout layout;
//    private String artist;
//    private List<String> artistIds;
//    private BorderColor borderColor;
//    private String cardBackId;
//    private Frame frame;
//    private List<FrameEffect> frameEffects;
//    private String illustrationId;

    ///////// Extra
    private List<Game> games;
    private LegalitiesDto legalities;
//    private Map<String, Relationship> relatedCards;
//    private Rarity rarity;
//    private String setName;
//    private SetType setType;
//    private String scryfallSetUri;
//    private String setId;

}
