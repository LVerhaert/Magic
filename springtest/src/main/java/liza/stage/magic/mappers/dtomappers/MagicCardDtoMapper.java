package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.enums.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {CardFaceDtoMapper.class})
public abstract class MagicCardDtoMapper {

    @Mapping(source = "relatedCards", target = "relatedCards", qualifiedByName = "toRelatedCards")
    @Mapping(source = "imageUris.small", target = "smallImageUri")
    @Mapping(source = "imageUris.large", target = "largeImageUri")
    public abstract MagicCardDto map(MagicCard magicCard);


    @Named("toRelatedCards")
    Map<String, Relationship> toRelatedCards(List<RelatedCard> relatedCardList) {
        Map<String, Relationship> relatedCards = new HashMap<>();
        if (relatedCardList != null) {
            for (RelatedCard relatedCard : relatedCardList) {
                String relatedId = relatedCard.getScryfallId();
                Relationship relationship = relatedCard.getRelationship();
                relatedCards.put(relatedId, relationship);
            }
        }
        return relatedCards;
    }
}
