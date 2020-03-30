package liza.stage.magic.models.dtos;

import liza.stage.magic.models.enums.Legality;
import lombok.Data;

@Data
public class LegalitiesDto {

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
