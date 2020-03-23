package liza.stage.springtest.mappers.importmappers;

import liza.stage.springtest.domain.ImageUris;
import liza.stage.springtest.jsonimport.model.ImageUrisJson;
import org.mapstruct.Mapper;

@Mapper
public interface ImageUrisMapper {
    ImageUris toEntity(ImageUrisJson imageUrisJson);
}
