
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class RelatedUris {
    private String edhrec;
    private String gatherer;
    private String mtgtop8;
    @JsonProperty("tcgplayer_decks")
    private String tcgplayerDecks;

}
