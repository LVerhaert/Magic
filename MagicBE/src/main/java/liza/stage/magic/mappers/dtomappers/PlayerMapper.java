package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.players.dtos.PlayerDto;
import liza.stage.magic.models.players.entities.PlayerEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CollectionMapper.class})
public abstract class PlayerMapper {
    public abstract PlayerDto toDto(PlayerEntity playerEntity);

    public abstract PlayerEntity toEntity(PlayerDto playerDto);
}
