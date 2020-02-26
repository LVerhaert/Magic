package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.Color;
import liza.stage.springtest.model.enums.ObjectType;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CardFace {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String artist;
    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("color_indicator")
    @ElementCollection
    private List<Color> colorIndicator;
    @ElementCollection
    private List<Color> colors;
    @JsonProperty("flavor_text")
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    @JsonProperty("illustration_id")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUris imageUris;
    private String loyalty;
    @JsonProperty("mana_cost")
    private String manaCost;
    private String name;
    private ObjectType object;
    @JsonProperty("oracle_text")
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;
    private String power;
    private String toughness;
    @JsonProperty("type_line")
    private String typeLine;
    private String watermark;

}
