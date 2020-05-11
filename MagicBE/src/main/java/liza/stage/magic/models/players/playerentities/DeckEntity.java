package liza.stage.magic.models.players.playerentities;

import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@Entity(name = "deck")
@Table(name = "deck")
public class DeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private PlayerEntity player;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "magiccard_deck",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "magiccard_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MagicCardEntity> magicCards;

    private String deckName;

    public List<MagicCardEntity> getMagicCardsPaged(Pageable pageable) {
        if (pageable.getOffset() >= magicCards.size()) {
            return Collections.emptyList();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > magicCards.size() ?
                magicCards.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<MagicCardEntity> subList = magicCards.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, magicCards.size()).getContent();
    }
}
