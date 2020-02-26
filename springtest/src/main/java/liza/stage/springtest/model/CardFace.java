package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.Color;
import lombok.Data;

import java.util.List;

@Data
public class CardFace {
    private String artist;
    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("color_indicator")
    private List<Color> colorIndicator;
    private List<Color> colors;
    @JsonProperty("flavor_text")
    private String flavorText;
    @JsonProperty("illustration_id")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUris imageUris;
    private String loyalty;
    @JsonProperty("mana_cost")
    private String manaCost;
    private String name;
    private String object;
    @JsonProperty("oracle_text")
    private String oracleText;
    private String power;
    private String toughness;
    @JsonProperty("type_line")
    private String typeLine;
    private String watermark;

}
