package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.magiccards.entities.CardFaceEntity;
import liza.stage.magic.models.magiccards.enums.Color;
import liza.stage.magic.models.magiccards.json.CardFaceJson;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CardFaceImportMapper {

    @Mapping(source = "colors", target = "colors", qualifiedByName = "toCFColors")
    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toCFColors")
    public abstract CardFaceEntity map(CardFaceJson cardFaceJson);

    @Named("toCFColors")
    List<Color> toCFColors(List<String> colorsJson) {
        List<Color> colors = new ArrayList<>();
        if (colorsJson != null) {
            for (String colorJson : colorsJson) {
                colors.add(Color.fromString(colorJson));
            }
        }
        return colors;
    }
}
