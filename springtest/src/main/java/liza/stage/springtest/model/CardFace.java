package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.Color;
import liza.stage.springtest.model.enums.ObjectType;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CardFace {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Type(type = "org.hibernate.type.TextType")
    private String artist;
    @JsonProperty("artist_id")
    @Type(type = "org.hibernate.type.TextType")
    private String artistId;
    @JsonProperty("color_indicator")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colorIndicator;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Color> colors;
    @JsonProperty("flavor_text")
    @Type(type = "org.hibernate.type.TextType")
    private String flavorText;
    @JsonProperty("illustration_id")
    @Type(type = "org.hibernate.type.TextType")
    private String illustrationId;
    @JsonProperty("image_uris")
    private ImageUris imageUris;
    @Type(type = "org.hibernate.type.TextType")
    private String loyalty;
    @JsonProperty("mana_cost")
    @Type(type = "org.hibernate.type.TextType")
    private String manaCost;
    @Type(type = "org.hibernate.type.TextType")
    private String name;
    private ObjectType object;
    @JsonProperty("oracle_text")
    @Type(type = "org.hibernate.type.TextType")
    private String oracleText;
    @Type(type = "org.hibernate.type.TextType")
    private String power;
    @Type(type = "org.hibernate.type.TextType")
    private String toughness;
    @JsonProperty("type_line")
    @Type(type = "org.hibernate.type.TextType")
    private String typeLine;
    @Type(type = "org.hibernate.type.TextType")
    private String watermark;

}
