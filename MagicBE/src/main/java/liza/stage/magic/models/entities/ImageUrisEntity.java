package liza.stage.magic.models.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Data
@Embeddable
@Table(name = "imageuris")
public class ImageUrisEntity {

    private String large;
    private String small;

}
