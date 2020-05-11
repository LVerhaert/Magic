package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.magiccards.magiccardentities.CardFaceEntity;
import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import liza.stage.magic.models.magiccards.magiccardjson.CardFaceJson;
import liza.stage.magic.models.magiccards.magiccardjson.MagicCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", uses = {/*RelatedCardImportMapper.class, */CardFaceImportMapper.class})
public abstract class MagicCardImportMapper {

    /*
    @Autowired
    private RelatedCardImportMapper relatedCardImportMapper;
     */
    @Autowired
    private CardFaceImportMapper cardFaceImportMapper;

    @Mapping(source = "idString", target = "scryfallId")
//    @Mapping(source = "set", target = "setId")
//    @Mapping(source = "allParts", target = "relatedCards", qualifiedByName = "toRelatedCards")
//    @Mapping(source = "lang", target = "language")
//    @Mapping(source = "cmc", target = "convManaCost")
//    @Mapping(source = "frame", target = "frame", qualifiedByName = "toFrame")
//    @Mapping(source = "colors", target = "colors", qualifiedByName = "toColors")
//    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toColors")
//    @Mapping(source = "colorIdentity", target = "colorIdentity", qualifiedByName = "toColors")
    @Mapping(source = "cardFaces", target = "cardFaces", qualifiedByName = "toCardFaces")
    @Mapping(source = "imageUris.png", target = "imageUris.large")
    public abstract MagicCardEntity map(MagicCardJson magicCardJson);

    /*
    @Named("toFrame")
    Frame toFrame(String frameJson) {
        return Frame.fromString(frameJson);
    }

    @Named("toColors")
    List<Color> toColors(List<String> colorsJson) {
        List<Color> colors = new ArrayList<>();
        if (colorsJson != null) {
            for (String colorJson : colorsJson) {
                colors.add(Color.fromString(colorJson));
            }
        }
        return colors;
    }
     */

    @Named("toCardFaces")
    List<CardFaceEntity> toCardFaces(List<CardFaceJson> cardFacesJson) {
        List<CardFaceEntity> cardFaceEntities = new ArrayList<>();
        if (cardFacesJson != null) {
            for (CardFaceJson cardFaceJson : cardFacesJson) {
                cardFaceEntities.add(cardFaceImportMapper.map(cardFaceJson));
            }
        }
        return cardFaceEntities;
    }

    /*
    @Named("toRelatedCards")
    List<RelatedCardEntity> toRelatedCards(List<RelatedCardJson> relatedCardsJson) {
        List<RelatedCardEntity> relatedCardEntities = new ArrayList<>();
        if (relatedCardsJson != null) {
            for (RelatedCardJson relatedCardJson : relatedCardsJson) {
                relatedCardEntities.add(relatedCardImportMapper.map(relatedCardJson));
            }
        }
        return relatedCardEntities;
    }
     */
}
