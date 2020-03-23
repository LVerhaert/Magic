package liza.stage.springtest.mappers.importmappers;

import liza.stage.springtest.domain.MagicCard;
import liza.stage.springtest.jsonimport.model.MagicCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MagicCardMapper {
    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "set", target = "setId")
    @Mapping(source = "allParts", target = "relatedCards")
    @Mapping(source = "lang", target = "language")
    @Mapping(source = "cmc", target = "convManaCost")
    MagicCard toEntity(MagicCardJson magicCardJson);


}
