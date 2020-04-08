package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.entities.CardFaceEntity;
import liza.stage.magic.models.enums.Color;
import liza.stage.magic.models.json.CardFaceJson;
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
                Color color = Color.fromString(colorJson);
                colors.add(color);
            }
        }
        return colors;
    }
}
