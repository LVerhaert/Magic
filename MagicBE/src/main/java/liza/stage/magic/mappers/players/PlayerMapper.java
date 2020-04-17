package liza.stage.magic.mappers.players;

import liza.stage.magic.models.players.dtos.PlayerDto;
import liza.stage.magic.models.players.entities.PlayerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {
    public abstract PlayerDto toDto(PlayerEntity playerEntity);

    public abstract PlayerEntity toEntity(PlayerDto playerDto);
}
