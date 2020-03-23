
package liza.stage.springtest.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ImageUris {

    private String large;
    private String small;

}
