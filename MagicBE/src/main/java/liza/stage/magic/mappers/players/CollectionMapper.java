package liza.stage.magic.mappers.players;

import liza.stage.magic.models.players.dtos.DeckDto;
import liza.stage.magic.models.players.dtos.MainCollectionDto;
import liza.stage.magic.models.players.entities.DeckEntity;
import liza.stage.magic.models.players.entities.MainCollectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CollectionMapper {
    public abstract MainCollectionDto toDto(MainCollectionEntity mainCollectionEntity);

    public abstract DeckDto toDto(DeckEntity deckEntity);

    public abstract MainCollectionEntity toEntity(MainCollectionDto mainCollectionDto);

    public abstract DeckEntity toEntity(DeckDto deckDto);
}
