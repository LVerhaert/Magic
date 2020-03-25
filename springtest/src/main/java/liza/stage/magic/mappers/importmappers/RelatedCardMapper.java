package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.RelatedCard;
import liza.stage.magic.jsonimport.model.RelatedCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RelatedCardMapper {
    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "component", target = "relationship")
    RelatedCard toEntity(RelatedCardJson relatedCardJson);
}
