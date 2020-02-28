
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Embeddable
public class Preview {

    @JsonProperty("previewed_at")
    @Type(type = "org.hibernate.type.TextType")
    private String previewedAt;
    @Type(type = "org.hibernate.type.TextType")
    private String source;
    @JsonProperty("source_uri")
    @Type(type = "org.hibernate.type.TextType")
    private String sourceUri;

}
