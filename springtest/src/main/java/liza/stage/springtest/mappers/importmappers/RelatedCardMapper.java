package liza.stage.springtest.mappers.importmappers;

import liza.stage.springtest.domain.RelatedCard;
import liza.stage.springtest.jsonimport.model.RelatedCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RelatedCardMapper {
    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "component", target = "relationship")
    RelatedCard toEntity(RelatedCardJson relatedCardJson);
}
