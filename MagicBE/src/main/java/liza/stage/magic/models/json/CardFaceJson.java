package liza.stage.magic.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CardFaceJson {
    private String artist;
    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("color_indicator")
    private List<String> colorIndicator;
    private List<String> colors;
    @JsonProperty("flavor_text")
    private String flavorText;
    @JsonProperty("illustration_id")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUrisJson imageUris;
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
