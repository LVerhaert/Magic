package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.MagicCardDtoMapper;
import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.models.entities.RelatedCard;
import liza.stage.magic.models.enums.Language;
import liza.stage.magic.models.enums.Rarity;
import liza.stage.magic.models.enums.Relationship;
import liza.stage.magic.models.enums.SetType;
import liza.stage.magic.repositories.MagicCardEntitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MagicCardService {
    @Autowired
    private final MagicCardEntitiesRepository repo;

    ////////////// Entities
    public void save(MagicCard magicCard) {
        repo.save(magicCard);
    }

    public List<MagicCard> findAll() {
        return repo.findAll();
    }

    public MagicCard findById(String id) {
        Optional<MagicCard> magicCard = repo.findById(id);
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
        return MagicCardDtoMapper.INSTANCE.map(magicCard);
    }

    public Map<MagicCardDto, Relationship> findAllDtoRelatedTo(String id) {
        MagicCardDto magicCard = findDtoById(id);
        return magicCard.getRelatedCards();
    }

    public Map<MagicCardDto, Relationship> findAllDtoRelatedTo(MagicCard magicCard) {
        return findAllDtoRelatedTo(magicCard.getScryfallId());
    }


}
