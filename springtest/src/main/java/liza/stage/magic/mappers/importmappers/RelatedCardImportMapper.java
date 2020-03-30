package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.json.RelatedCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelatedCardImportMapper {
    RelatedCardImportMapper INSTANCE = Mappers.getMapper(RelatedCardImportMapper.class);

    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "component", target = "relationship")
    RelatedCard map(RelatedCardJson relatedCardJson);
}
