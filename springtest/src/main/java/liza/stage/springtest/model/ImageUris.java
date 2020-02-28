
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Embeddable
public class ImageUris {

    @JsonProperty("art_crop")
    @Type(type = "org.hibernate.type.TextType")
    private String artCrop;
    @JsonProperty("border_crop")
    @Type(type = "org.hibernate.type.TextType")
    private String borderCrop;
    @Type(type = "org.hibernate.type.TextType")
    private String large;
    @Type(type = "org.hibernate.type.TextType")
    private String normal;
    @Type(type = "org.hibernate.type.TextType")
    private String png;
    @Type(type = "org.hibernate.type.TextType")
    private String small;

}
