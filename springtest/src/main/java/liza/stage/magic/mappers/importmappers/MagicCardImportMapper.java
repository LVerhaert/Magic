package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.entities.CardFace;
import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.enums.Color;
import liza.stage.magic.models.enums.Frame;
import liza.stage.magic.models.json.CardFaceJson;
import liza.stage.magic.models.json.MagicCardJson;
import liza.stage.magic.models.json.RelatedCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {RelatedCardImportMapper.class, CardFaceImportMapper.class})
public abstract class MagicCardImportMapper {

    @Autowired
    private RelatedCardImportMapper relatedCardImportMapper;
    @Autowired
    private CardFaceImportMapper cardFaceImportMapper;

    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "set", target = "setId")
    @Mapping(source = "allParts", target = "relatedCards", qualifiedByName = "toRelatedCards")
    @Mapping(source = "lang", target = "language")
    @Mapping(source = "cmc", target = "convManaCost")
    @Mapping(source = "frame", target = "frame", qualifiedByName = "toFrame")
    @Mapping(source = "colors", target = "colors", qualifiedByName = "toColors")
    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toColors")
    @Mapping(source = "colorIdentity", target = "colorIdentity", qualifiedByName = "toColors")
    @Mapping(source = "cardFaces", target = "cardFaces", qualifiedByName = "toCardFaces")
    public abstract MagicCard map(MagicCardJson magicCardJson);

    @Named("toFrame")
    Frame toFrame(String frameJson) {
        Frame frame = Frame.fromString(frameJson);
        return frame;
    }

    @Named("toColors")
    List<Color> toColors(List<String> colorsJson) {
        List<Color> colors = new ArrayList<>();
        if (colorsJson != null) {
            for (String colorJson : colorsJson) {
                Color color = Color.fromString(colorJson);
                colors.add(color);
            }
        }
        return colors;
    }

    @Named("toCardFaces")
    List<CardFace> toCardFaces(List<CardFaceJson> cardFacesJson) {
        List<CardFace> cardFaces = new ArrayList<>();
        if (cardFacesJson != null) {
            for (CardFaceJson cardFaceJson : cardFacesJson) {
                CardFace cardFace = cardFaceImportMapper.map(cardFaceJson);
                cardFaces.add(cardFace);
            }
        }
        return cardFaces;
    }


    @Named("toRelatedCards")
    List<RelatedCard> toRelatedCards(List<RelatedCardJson> relatedCardsJson) {
        List<RelatedCard> relatedCards = new ArrayList<>();
        if (relatedCardsJson != null) {
            for (RelatedCardJson relatedCardJson : relatedCardsJson) {
                RelatedCard relatedCard = relatedCardImportMapper.map(relatedCardJson);
                relatedCards.add(relatedCard);
            }
        }
        return relatedCards;
    }
}
