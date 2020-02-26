
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class RelatedUris {

    private Uri edhrec;
    private Uri gatherer;
    private Uri mtgtop8;
    @JsonProperty("tcgplayer_decks")
    private Uri tcgplayerDecks;

}
