package liza.stage.springtest.mappers.importmappers;

import liza.stage.springtest.domain.CardFace;
import liza.stage.springtest.jsonimport.model.CardFaceJson;
import org.mapstruct.Mapper;

@Mapper
public interface CardFaceMapper {
    CardFace toEntity(CardFaceJson cardFaceJson);

}
