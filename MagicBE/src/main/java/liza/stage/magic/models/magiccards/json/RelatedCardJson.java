package liza.stage.magic.models.magiccards.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedCardJson {
    @JsonProperty("id")
    private String idString;
    private String component;
    private String name;
    private String object;
    @JsonProperty("type_line")
    private String typeLine;
    private String uri;

}
