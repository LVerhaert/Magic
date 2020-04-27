package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.players.dtos.DeckDto;
import liza.stage.magic.models.players.dtos.MainCollectionDto;
import liza.stage.magic.models.players.entities.DeckEntity;
import liza.stage.magic.models.players.entities.MainCollectionEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {MagicCardMapper.class})
public abstract class CollectionMapper {

    @Mapping(source = "magicCards", target = "magicCardIds", qualifiedByName = "toMagicCardIds")
    public abstract MainCollectionDto toDto(MainCollectionEntity mainCollectionEntity);

    @Mapping(source = "magicCards", target = "magicCardIds", qualifiedByName = "toMagicCardIds")
    public abstract DeckDto toDto(DeckEntity deckEntity);

    public abstract MainCollectionEntity toEntity(MainCollectionDto mainCollectionDto);

    public abstract DeckEntity toEntity(DeckDto deckDto);

    @Named("toMagicCardIds")
    List<String> toMagicCardIds(List<MagicCardEntity> magicCards) {
        List<String> magicCardIds = new ArrayList<>();
        for (MagicCardEntity magicCard : magicCards) {
            magicCardIds.add(magicCard.getScryfallId());
        }
        return magicCardIds;
    }
}
