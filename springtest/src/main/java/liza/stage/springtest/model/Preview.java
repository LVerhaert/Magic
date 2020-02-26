
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Preview {

    @JsonProperty("previewed_at")
    private String previewedAt;
    private String source;
    @JsonProperty("source_uri")
    private String sourceUri;

}
