package liza.stage.magic.models.players.playerdtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DeckDto extends CollectionDto {
    private String deckName;

    public DeckDto(String name) {
        super();
        deckName = name;
    }
}
