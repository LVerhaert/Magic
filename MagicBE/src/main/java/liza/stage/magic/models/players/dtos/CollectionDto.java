package liza.stage.magic.models.players.dtos;

import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import lombok.Data;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public abstract class CollectionDto {
    private int id;
    private List<MagicCardDto> magicCards;

    protected CollectionDto() {
        magicCards = new ArrayList<>();
    }

    public List<MagicCardDto> getMagicCardsPaged(Pageable pageable) {
        if (pageable.getOffset() >= magicCards.size()) {
            return Collections.emptyList();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > magicCards.size() ?
                magicCards.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<MagicCardDto> subList = magicCards.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, magicCards.size()).getContent();
    }
}
