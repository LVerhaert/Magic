package liza.stage.magic.models.magiccards.magiccarddtos;

import lombok.Data;


@Data
public class CardFaceDto {
    /////////// Core
    private Long id;
    private String name;
    private String typeLine;
    private String oracleText;
//    private String flavorText;

    /////////// Gameplay
//    private List<Color> colors;
//    private List<Color> colorIndicator;
//    private String loyalty;
//    private String manaCost;
//    private String power;
//    private String toughness;

    ////////// Print
    private String smallImageUri;
    private String largeImageUri;
//    private String artist;
//    private String artistId;
//    private String illustrationId;

}
