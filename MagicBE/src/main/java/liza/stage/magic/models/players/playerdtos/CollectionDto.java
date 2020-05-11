package liza.stage.magic.models.players.playerdtos;

import lombok.Data;

import java.util.List;

@Data
public abstract class CollectionDto {
    private int id;
    private List<String> magicCardIds;
}
