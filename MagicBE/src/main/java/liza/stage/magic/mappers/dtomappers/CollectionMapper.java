package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import liza.stage.magic.models.players.playerdtos.DeckDto;
import liza.stage.magic.models.players.playerdtos.MainCollectionDto;
import liza.stage.magic.models.players.playerentities.DeckEntity;
import liza.stage.magic.models.players.playerentities.MainCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {MagicCardMapper.class})
public abstract class CollectionMapper {

    @Mapping(source = "magicCards", target = "magicCardIds", qualifiedByName = "toMagicCardIds")
//    @Mapping(source = "player", target = "player", ignore = true)
    public abstract MainCollectionDto map(MainCollectionEntity mainCollectionEntity);

    @Mapping(source = "magicCards", target = "magicCardIds", qualifiedByName = "toMagicCardIds")
//    @Mapping(source = "player", target = "player", ignore = true)
    public abstract DeckDto map(DeckEntity deckEntity);

    public abstract MainCollectionEntity map(MainCollectionDto mainCollectionDto);

    public abstract DeckEntity map(DeckDto deckDto);

    @Named("toMagicCardIds")
    List<String> toMagicCardIds(List<MagicCardEntity> magicCards) {
        List<String> magicCardIds = new ArrayList<>();
        for (MagicCardEntity magicCard : magicCards) {
            magicCardIds.add(magicCard.getScryfallId());
        }
        return magicCardIds;
    }
}
