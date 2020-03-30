package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.enums.Relationship;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class MagicCardDtoMapper {
    public static MagicCardDtoMapper INSTANCE = Mappers.getMapper(MagicCardDtoMapper.class);
    @Autowired
    private final MagicCardService service;

    @Mapping(source = "relatedCards", target = "relatedCards", qualifiedByName = "toRelatedCards")
    @Mapping(source = "imageUris.small", target = "smallImageUri")
    @Mapping(source = "imageUris.large", target = "largeImageUri")
    public abstract MagicCardDto map(MagicCard magicCard);


    @Named("toRelatedCards")
    Map<MagicCardDto, Relationship> toRelatedCards(List<RelatedCard> relatedCardList) {
        Map<MagicCardDto, Relationship> relatedCards = new HashMap<>();
        if (relatedCardList != null) {
            for (RelatedCard relatedCard : relatedCardList) {
                MagicCardDto magicCardDto = service.findDtoById(relatedCard.getScryfallId());
                Relationship relationship = relatedCard.getRelationship();
                relatedCards.put(magicCardDto, relationship);
            }
        }
        return relatedCards;
    }
}
