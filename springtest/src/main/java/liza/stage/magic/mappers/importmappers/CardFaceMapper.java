package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.CardFace;
import liza.stage.magic.jsonimport.model.CardFaceJson;
import org.mapstruct.Mapper;

@Mapper
public interface CardFaceMapper {
    CardFace toEntity(CardFaceJson cardFaceJson);

}
