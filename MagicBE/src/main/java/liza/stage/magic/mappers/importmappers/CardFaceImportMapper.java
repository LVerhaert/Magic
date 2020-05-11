package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.magiccards.magiccardentities.CardFaceEntity;
import liza.stage.magic.models.magiccards.magiccardjson.CardFaceJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CardFaceImportMapper {

    //    @Mapping(source = "colors", target = "colors", qualifiedByName = "toCFColors")
//    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toCFColors")
    @Mapping(source = "imageUris.png", target = "imageUris.large")
    public abstract CardFaceEntity map(CardFaceJson cardFaceJson);

    /*
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
     */
}
