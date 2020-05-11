package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.magiccards.magiccarddtos.MagicCardDto;
import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CardFaceMapper.class})
public abstract class MagicCardMapper {

    @Mapping(source = "imageUris.small", target = "smallImageUri")
    @Mapping(source = "imageUris.large", target = "largeImageUri")
//    @Mapping(source = "relatedCards", target = "relatedCards", qualifiedByName = "toRelatedCards")
    public abstract MagicCardDto map(MagicCardEntity magicCardEntity);

    @Mapping(source = "smallImageUri", target = "imageUris.small")
    @Mapping(source = "largeImageUri", target = "imageUris.large")
//    @Mapping(source = "relatedCards", target = "relatedCards", ignore = true)
    public abstract MagicCardEntity map(MagicCardDto magicCardDto);

    /*
    @Named("toRelatedCards")
    Map<String, Relationship> toRelatedCards(List<RelatedCardEntity> relatedCardEntityList) {
        Map<String, Relationship> relatedCards = new HashMap<>();
        if (relatedCardEntityList != null) {
            for (RelatedCardEntity relatedCardEntity : relatedCardEntityList) {
                String relatedId = relatedCardEntity.getScryfallId();
                Relationship relationship = relatedCardEntity.getRelationship();
                relatedCards.put(relatedId, relationship);
            }
        }
        return relatedCards;
    }
     */

}
