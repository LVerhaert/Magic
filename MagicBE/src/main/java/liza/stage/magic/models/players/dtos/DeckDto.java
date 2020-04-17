package liza.stage.magic.models.players.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeckDto extends CollectionDto {
    private String deckName;
}
