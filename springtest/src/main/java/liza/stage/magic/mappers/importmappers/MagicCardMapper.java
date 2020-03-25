package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.CardFace;
import liza.stage.magic.domain.MagicCard;
import liza.stage.magic.domain.enums.Color;
import liza.stage.magic.domain.enums.Frame;
import liza.stage.magic.jsonimport.model.CardFaceJson;
import liza.stage.magic.jsonimport.model.MagicCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class MagicCardMapper {
    public static MagicCardMapper INSTANCE = Mappers.getMapper(MagicCardMapper.class);

    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "set", target = "setId")
    @Mapping(source = "allParts", target = "relatedCards")
    @Mapping(source = "lang", target = "language")
    @Mapping(source = "cmc", target = "convManaCost")
    @Mapping(source = "frame", target = "frame", qualifiedByName = "toFrame")
    @Mapping(source = "colors", target = "colors", qualifiedByName = "toColors")
    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toColors")
    @Mapping(source = "colorIdentity", target = "colorIdentity", qualifiedByName = "toColors")
    @Mapping(source = "cardFaces", target = "cardFaces", qualifiedByName = "toCardFaces")
    public abstract MagicCard toEntity(MagicCardJson magicCardJson);

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
                CardFace cardFace = CardFaceMapper.INSTANCE.toEntity(cardFaceJson);
                cardFaces.add(cardFace);
            }
        }
        return cardFaces;
    }
}
