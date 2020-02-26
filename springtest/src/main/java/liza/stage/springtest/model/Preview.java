
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Preview {

    @JsonProperty("previewed_at")
    private String previewedAt;
    private String source;
    @JsonProperty("source_uri")
    private String sourceUri;

}
