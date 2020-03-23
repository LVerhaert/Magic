package liza.stage.springtest.mappers.importmappers;

import liza.stage.springtest.domain.Legalities;
import liza.stage.springtest.jsonimport.model.LegalitiesJson;
import org.mapstruct.Mapper;

@Mapper
public interface LegalitiesMapper {
    Legalities toEntity(LegalitiesJson legalitiesJson);
}
