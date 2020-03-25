package liza.stage.magic.mappers.importmappers;

import liza.stage.magic.domain.MagicCard;
import liza.stage.magic.domain.enums.Frame;
import liza.stage.magic.jsonimport.model.MagicCardJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class MagicCardMapper {
    public static MagicCardMapper INSTANCE = Mappers.getMapper(MagicCardMapper.class);

    @Mapping(source = "idString", target = "scryfallId")
    @Mapping(source = "set", target = "setId")
    @Mapping(source = "allParts", target = "relatedCards")
    @Mapping(source = "lang", target = "language")
    @Mapping(source = "cmc", target = "convManaCost")
    @Mapping(source = "frame", target = "frame", qualifiedByName = "toFrame")
    public abstract MagicCard toEntity(MagicCardJson magicCardJson);

    @Named("toFrame")
    Frame toFrame(String frameJson) {
        Frame frame = Frame.fromString(frameJson);
        return frame;
    }

}
