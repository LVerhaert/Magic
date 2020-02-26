
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ImageUris {

    @JsonProperty("art_crop")
    private Uri artCrop;
    @JsonProperty("border_crop")
    private Uri borderCrop;
    private Uri large;
    private Uri normal;
    private Uri png;
    private Uri small;

}
