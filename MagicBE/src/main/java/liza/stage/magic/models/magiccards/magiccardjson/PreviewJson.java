package liza.stage.magic.models.magiccards.magiccardjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PreviewJson {

    @JsonProperty("previewed_at")
    private String previewedAt;
    private String source;
    @JsonProperty("source_uri")
    private String sourceUri;

}
