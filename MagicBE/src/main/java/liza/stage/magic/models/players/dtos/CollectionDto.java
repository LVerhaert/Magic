package liza.stage.magic.models.players.dtos;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import lombok.Data;

import java.util.List;

@Data
public abstract class CollectionDto {
    private int id;
    private List<MagicCardEntity> magicCards;
}
