package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.entities.RelatedCardEntity;
import liza.stage.magic.models.json.RelatedCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RelatedCardImportMapper {

    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "component", target = "relationship")
    RelatedCardEntity map(RelatedCardJson relatedCardJson);
}
