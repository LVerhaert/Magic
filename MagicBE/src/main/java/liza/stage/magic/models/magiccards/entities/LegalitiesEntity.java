package liza.stage.magic.models.magiccards.entities;

import liza.stage.magic.models.magiccards.enums.Legality;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Data
@Embeddable
@Table(name = "legalities")
public class LegalitiesEntity {

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
