package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.CardFace;
import liza.stage.magic.domain.enums.Color;
import liza.stage.magic.jsonimport.model.CardFaceJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class CardFaceMapper {
    public static CardFaceMapper INSTANCE = Mappers.getMapper(CardFaceMapper.class);

    @Mapping(source = "colors", target = "colors", qualifiedByName = "toColors")
    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toColors")
    public abstract CardFace toEntity(CardFaceJson cardFaceJson);

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
}
