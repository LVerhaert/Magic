package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.entities.CardFace;
import liza.stage.magic.models.json.CardFaceJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CardFaceImportMapper {

    @Mapping(source = "colors", target = "colors", qualifiedByName = "toColors")
    @Mapping(source = "colorIndicator", target = "colorIndicator", qualifiedByName = "toColors")
    public abstract CardFace map(CardFaceJson cardFaceJson);

}
