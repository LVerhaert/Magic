package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.MagicCardDtoMapper;
import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.models.magiccards.dtos.PagingResult;
import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.magiccards.entities.RelatedCardEntity;
import liza.stage.magic.models.magiccards.enums.Relationship;
import liza.stage.magic.repositories.MagicCardEntitiesRepository;
import liza.stage.magic.repositories.RelatedCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MagicCardService {
    private final MagicCardEntitiesRepository magicCardEntitiesRepository;
    private final RelatedCardRepository relatedCardRepository;
    private final MagicCardDtoMapper magicCardDtoMapper;

    ////////////// Entities

    public MagicCardEntity findEntityById(String id) {
        Optional<MagicCardEntity> magicCardEntity = magicCardEntitiesRepository.findById(id);
        return magicCardEntity.orElse(null);
    }

    public List<MagicCardEntity> findEntitiesById(List<String> ids, Pageable pageable) {
        return magicCardEntitiesRepository.findAllByScryfallIdIn(ids, pageable);
    }

    public List<MagicCardEntity> findEntitiesById(List<String> ids) {
        return (List<MagicCardEntity>) magicCardEntitiesRepository.findAllById(ids);
    }

    public List<MagicCardEntity> findPaged(Pageable pageable) {
        Page<MagicCardEntity> page = magicCardEntitiesRepository.findAll(pageable);
        return page.getContent();
    }

    public long getSize() {
        return magicCardEntitiesRepository.count();
    }

    //////////////// Entity -> DTO
    public List<MagicCardDto> fromEntity(List<MagicCardEntity> magicCardEntities) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : magicCardEntities) {
            magicCards.add(fromEntity(magicCardEntity));
        }
        return magicCards;
    }

    private MagicCardDto fromEntity(MagicCardEntity magicCardEntity) {
        MagicCardDto magicCardDto = magicCardDtoMapper.map(magicCardEntity);
        if (magicCardDto.getCardFaces().size() > 0) {
            magicCardDto.setSmallImageUri(magicCardDto.getCardFaces().get(0).getSmallImageUri());
            magicCardDto.setLargeImageUri(magicCardDto.getCardFaces().get(0).getLargeImageUri());
        }
        return magicCardDto;
    }

    //////////////// DTO -> Entity
    private MagicCardEntity toEntity(MagicCardDto magicCardDto) {
        MagicCardEntity magicCardEntity = magicCardDtoMapper.map(magicCardDto);
        List<RelatedCardEntity> relatedCardEntities = new ArrayList<>();
        if (magicCardDto.getRelatedCards() != null) {
            for (Map.Entry<String, Relationship> relatedCardDto : magicCardDto.getRelatedCards().entrySet()) {
                String relatedCardString = relatedCardDto.getKey();
                Relationship relationship = relatedCardDto.getValue();
                relatedCardEntities.add((RelatedCardEntity) relatedCardRepository.findAllByScryfallId(relatedCardString));
            }
            magicCardEntity.setRelatedCards(relatedCardEntities);
        }
        return magicCardEntity;
    }

    ////////////// DTO's
//    public Iterable<MagicCardDto> findAll() {
//        return fromEntity(findAllEntities());
//    }

    public MagicCardDto findById(String id) {
        return fromEntity(findEntityById(id));
    }

    public Map<MagicCardDto, Relationship> findAllRelatedTo(String id) {
        MagicCardDto magicCard = findById(id);
        Map<String, Relationship> relatedCards = magicCard.getRelatedCards();
        Map<MagicCardDto, Relationship> related = new HashMap<>();
        if (relatedCards != null) {
            for (Map.Entry<String, Relationship> relatedCard : relatedCards.entrySet()) {
                related.put(findById(relatedCard.getKey()), relatedCard.getValue());
            }
        }
        return related;
    }

    public Map<MagicCardDto, Relationship> findAllRelatedTo(MagicCardEntity magicCardEntity) {
        return findAllRelatedTo(magicCardEntity.getScryfallId());
    }

    ////////////// Search
    public List<MagicCardDto> searchByName(String term) {
        List<MagicCardEntity> magicCards = magicCardEntitiesRepository.findTop10ByNameContains(term);
        return fromEntity(magicCards);
    }

    /////////// Paging
    public PagingResult<MagicCardDto> findOnePageDto(Pageable pageable) {
        List<MagicCardEntity> magicCardEntities = findPaged(pageable);
        return new PagingResult<>(fromEntity(magicCardEntities), getSize());
    }

}
