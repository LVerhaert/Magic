package liza.stage.magic.models.magiccards.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedUrisJson {
    private String edhrec;
    private String gatherer;
    private String mtgtop8;
    @JsonProperty("tcgplayer_decks")
    private String tcgplayerDecks;

}
