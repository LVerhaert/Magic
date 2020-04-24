package liza.stage.magic.models.magiccards.dtos;

import liza.stage.magic.models.magiccards.enums.Color;

import lombok.Data;

import java.util.List;


@Data
public class CardFaceDto {
    private Long id;
    private String artist;
    private String artistId;
    private List<Color> colorIndicator;
    private List<Color> colors;
    private String flavorText;
    private String illustrationId;
    private String smallImageUri;
    private String largeImageUri;
    private String loyalty;
    private String manaCost;
    private String name;
    private String oracleText;
    private String power;
    private String toughness;
    private String typeLine;

}
