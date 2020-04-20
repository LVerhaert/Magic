package liza.stage.magic.mappers.players;

import liza.stage.magic.mappers.dtomappers.MagicCardDtoMapper;
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

@Mapper(componentModel = "spring", uses = {MagicCardDtoMapper.class})
public abstract class CollectionMapper {

    @Mapping(source = "magicCards", target = "magicCards")
//    @Mapping(source = "magicCards", target = "magicCardsStrings", qualifiedByName = "cardsToStrings")
    public abstract MainCollectionDto toDto(MainCollectionEntity mainCollectionEntity);

    @Mapping(source = "magicCards", target = "magicCards")
//    @Mapping(source = "magicCards", target = "magicCardsStrings", qualifiedByName = "cardsToStrings")
    public abstract DeckDto toDto(DeckEntity deckEntity);

    @Named("cardsToStrings")
    List<String> cardsToStrings(List<MagicCardEntity> magicCards) {
        ArrayList<String> cardStrings = new ArrayList<>();
        if (magicCards != null) {
            for (MagicCardEntity magicCard : magicCards) {
                cardStrings.add(magicCard.getScryfallId());
            }
        }
        return cardStrings;
    }

    public abstract MainCollectionEntity toEntity(MainCollectionDto mainCollectionDto);

    public abstract DeckEntity toEntity(DeckDto deckDto);

}
