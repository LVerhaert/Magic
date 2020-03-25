package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.ImageUris;
import liza.stage.magic.jsonimport.model.ImageUrisJson;
import org.mapstruct.Mapper;

@Mapper
public interface ImageUrisMapper {
    ImageUris toEntity(ImageUrisJson imageUrisJson);
}
