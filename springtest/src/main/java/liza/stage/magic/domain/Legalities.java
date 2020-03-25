
package liza.stage.magic.domain;

import liza.stage.magic.domain.enums.Legality;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Legalities {

    private Legality brawl;
    private Legality commander;
    private Legality duel;
    private Legality future;
    private Legality historic;
    private Legality legacy;
    private Legality modern;
    private Legality oldschool;
    private Legality pauper;
    private Legality penny;
    private Legality pioneer;
    private Legality standard;
    private Legality vintage;

}
