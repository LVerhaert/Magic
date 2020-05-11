package liza.stage.magic.models.magiccards.magiccardjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUrisJson {
    @JsonProperty("art_crop")
    private String artCrop;
    @JsonProperty("border_crop")
    private String borderCrop;
    //    @JsonProperty("png")
    private String large;
    private String normal;
    private String png;
    private String small;

}
