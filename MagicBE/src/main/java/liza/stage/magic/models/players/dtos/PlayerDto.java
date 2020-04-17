package liza.stage.magic.models.players.dtos;

import liza.stage.magic.models.players.entities.DeckEntity;
import liza.stage.magic.models.players.entities.MainCollectionEntity;
import lombok.Data;

import java.util.List;

@Data
public class PlayerDto {
    private int id;
    private String name;
    private MainCollectionEntity mainCollection;
    private List<DeckEntity> decks;
}
