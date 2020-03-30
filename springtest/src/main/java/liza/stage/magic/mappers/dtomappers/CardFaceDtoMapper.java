package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.dtos.CardFaceDto;
import liza.stage.magic.models.entities.CardFace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class CardFaceDtoMapper {
    public static CardFaceDtoMapper INSTANCE = Mappers.getMapper(CardFaceDtoMapper.class);

    @Mapping(source = "imageUris.small", target = "smallImageUri")
    @Mapping(source = "imageUris.large", target = "largeImageUri")
    public abstract CardFaceDto map(CardFace cardFace);

}
