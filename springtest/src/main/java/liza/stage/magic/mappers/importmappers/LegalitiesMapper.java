package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.Legalities;
import liza.stage.magic.jsonimport.model.LegalitiesJson;
import org.mapstruct.Mapper;

@Mapper
public interface LegalitiesMapper {
    Legalities toEntity(LegalitiesJson legalitiesJson);
}
