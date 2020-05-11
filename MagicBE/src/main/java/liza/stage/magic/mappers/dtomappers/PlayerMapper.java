package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.players.playerdtos.PlayerDto;
import liza.stage.magic.models.players.playerentities.PlayerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CollectionMapper.class})
public abstract class PlayerMapper {
    public abstract PlayerDto map(PlayerEntity playerEntity);

    public abstract PlayerEntity map(PlayerDto playerDto);

}
