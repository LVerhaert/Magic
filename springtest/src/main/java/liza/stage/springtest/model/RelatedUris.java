
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Embeddable
public class RelatedUris {
    private String edhrec;
    @Type(type = "org.hibernate.type.TextType")
    private String gatherer;
    @Type(type = "org.hibernate.type.TextType")
    private String mtgtop8;
    @JsonProperty("tcgplayer_decks")
    @Type(type = "org.hibernate.type.TextType")
    private String tcgplayerDecks;

}
