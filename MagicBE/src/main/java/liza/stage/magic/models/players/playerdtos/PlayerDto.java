package liza.stage.magic.models.players.playerdtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PlayerDto {
    private int id;
    private String name;
    private MainCollectionDto mainCollection;
    private List<DeckDto> decks;

    public PlayerDto(String name) {
        this.name = name;
        mainCollection = new MainCollectionDto();
        decks = new ArrayList<>();
    }
}
