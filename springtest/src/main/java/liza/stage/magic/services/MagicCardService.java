package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.MagicCardDtoMapper;
import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.dtos.PagingResult;
import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.enums.Language;
import liza.stage.magic.models.enums.Rarity;
import liza.stage.magic.models.enums.Relationship;
import liza.stage.magic.models.enums.SetType;
import liza.stage.magic.repositories.MagicCardEntitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MagicCardService {
    private final MagicCardEntitiesRepository magicCardEntitiesRepository;
    private final MagicCardDtoMapper magicCardDtoMapper;

    ////////////// Entities
    public void save(MagicCard magicCard) {
        magicCardEntitiesRepository.save(magicCard);
    }

    public List<MagicCard> findAll() {
        return magicCardEntitiesRepository.findAll();
    }

    public MagicCard findById(String id) {
        Optional<MagicCard> magicCard = magicCardEntitiesRepository.findById(id);
        return magicCard.orElse(null);
    }

    public List<MagicCard> findAllRelatedTo(String id) {
        ArrayList<MagicCard> relatedCards = new ArrayList<>();
        for (RelatedCard relatedCard : findById(id).getRelatedCards())
            relatedCards.add(findById(relatedCard.getScryfallId()));
        return relatedCards;
    }

    ////////////// DTO's
    public List<MagicCardDto> findDtoByRarity(Rarity rarity) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCard magicCard : findAll()) {
            if (magicCard.getRarity().equals(rarity)) {
                magicCards.add(toDto(magicCard));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findDtoByLanguage(Language language) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCard magicCard : findAll()) {
            if (magicCard.getLanguage().equals(language)) {
                magicCards.add(toDto(magicCard));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findDtoBySetType(SetType setType) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCard magicCard : findAll()) {
            if (magicCard.getSetType().equals(setType)) {
                magicCards.add(toDto(magicCard));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findDtoBySetName(String setName) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCard magicCard : findAll()) {
            if (magicCard.getSetName().equals(setName)) {
                magicCards.add(toDto(magicCard));
            }
        }
        return magicCards;
    }


    public Iterable<MagicCardDto> findAllDto() {
        return toDto(findAll());
    }

    public MagicCardDto findDtoById(String id) {
        return toDto(findById(id));
    }

    private List<MagicCardDto> toDto(List<MagicCard> magicCards) {
        List<MagicCardDto> magicCardDtos = new ArrayList<>();
        for (MagicCard magicCard : magicCards) {
            magicCardDtos.add(toDto(magicCard));
        }
        return magicCardDtos;
    }

    private MagicCardDto toDto(MagicCard magicCard) {
        return magicCardDtoMapper.map(magicCard);
    }

    public Map<MagicCardDto, Relationship> findAllDtoRelatedTo(String id) {
        MagicCardDto magicCard = findDtoById(id);
        Map<String, Relationship> relatedCards = magicCard.getRelatedCards();
        Map<MagicCardDto, Relationship> related = new HashMap<>();
        if (relatedCards != null) {
            for (Map.Entry<String, Relationship> relatedCard : relatedCards.entrySet()) {
                related.put(findDtoById(relatedCard.getKey()), relatedCard.getValue());
            }
        }
        return related;
    }

    public Map<MagicCardDto, Relationship> findAllDtoRelatedTo(MagicCard magicCard) {
        return findAllDtoRelatedTo(magicCard.getScryfallId());
    }

    ////////////// Search
    public List<MagicCardDto> searchDtoByName(String term) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCard magicCard : findAll()) {
            if (magicCard.getName().toLowerCase().contains(term.toLowerCase())) {
                magicCards.add(toDto(magicCard));
            }
        }
        return magicCards;
    }

    public PagingResult<MagicCardDto> findOnePageDto(int pageIndex, int pageSize) {
        List<MagicCard> magicCards = findAll();
        int begin = pageSize * (pageIndex-1);
        int end = pageSize * (pageIndex-1) + pageSize;
        if (begin >= magicCards.size() || begin < 0) {
            System.out.println("Index out of bounds: begin=" + begin + ", end=" + end + ", size=" + magicCards.size());
            return new PagingResult<>();
        } else if (end >= magicCards.size()) {
            end = magicCards.size();
        }
        System.out.println("begin=" + begin + ", end=" + end + ", size=" + magicCards.size());
        return new PagingResult<MagicCardDto>(toDto(magicCards.subList(begin, end)));
    }

}
