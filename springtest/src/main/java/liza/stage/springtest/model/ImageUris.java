
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ImageUris {

    @JsonProperty("art_crop")
    private String artCrop;
    @JsonProperty("border_crop")
    private String borderCrop;
    private String large;
    private String normal;
    private String png;
    private String small;

}
