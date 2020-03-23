
package liza.stage.springtest.jsonimport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ImageUrisJson {

    @JsonProperty("art_crop")
    private String artCrop;
    @JsonProperty("border_crop")
    private String borderCrop;
    private String large;
    private String normal;
    private String png;
    private String small;

}
