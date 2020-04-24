package liza.stage.magic.mappers.dtomappers;

import liza.stage.magic.models.magiccards.dtos.CardFaceDto;
import liza.stage.magic.models.magiccards.entities.CardFaceEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class CardFaceMapper {

    @Mapping(source = "imageUris.small", target = "smallImageUri")
    @Mapping(source = "imageUris.large", target = "largeImageUri")
    public abstract CardFaceDto map(CardFaceEntity cardFaceEntity);

    @Mapping(source = "smallImageUri", target = "imageUris.small")
    @Mapping(source = "largeImageUri", target = "imageUris.large")
    public abstract CardFaceEntity map(CardFaceDto cardFaceDto);

}
