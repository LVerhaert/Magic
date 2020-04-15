package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.MagicCardDtoMapper;
import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.dtos.PagingResult;
import liza.stage.magic.models.entities.MagicCardEntity;
import liza.stage.magic.models.enums.Language;
import liza.stage.magic.models.enums.Rarity;
import liza.stage.magic.models.enums.Relationship;
import liza.stage.magic.models.enums.SetType;
import liza.stage.magic.repositories.MagicCardEntitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MagicCardService {
    private final MagicCardEntitiesRepository magicCardEntitiesRepository;
    private final MagicCardDtoMapper magicCardDtoMapper;

    ////////////// Entities
    public List<MagicCardEntity> findAllEntities() {
        return (List<MagicCardEntity>) magicCardEntitiesRepository.findAll();
    }

    public MagicCardEntity findEntityById(String id) {
        Optional<MagicCardEntity> magicCardEntity = magicCardEntitiesRepository.findById(id);
        return magicCardEntity.orElse(null);
    }

    public List<MagicCardEntity> findPaged(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MagicCardEntity> page = magicCardEntitiesRepository.findAll(pageable);
        return page.getContent();
    }

    public long getSize() {
        return magicCardEntitiesRepository.count();
    }

    //////////////// Entity -> DTO
    private List<MagicCardDto> fromEntity(List<MagicCardEntity> magicCardEntityEntities) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : magicCardEntityEntities) {
            magicCards.add(fromEntity(magicCardEntity));
        }
        return magicCards;
    }

    private MagicCardDto fromEntity(MagicCardEntity magicCardEntity) {
        return magicCardDtoMapper.map(magicCardEntity);
    }

    ////////////// DTO's
    public Iterable<MagicCardDto> findAll() {
        return fromEntity(findAllEntities());
    }

    public MagicCardDto findById(String id) {
        return fromEntity(findEntityById(id));
    }

    public List<MagicCardDto> findByRarity(Rarity rarity) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : findAllEntities()) {
            if (magicCardEntity.getRarity().equals(rarity)) {
                magicCards.add(fromEntity(magicCardEntity));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findByLanguage(Language language) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : findAllEntities()) {
            if (magicCardEntity.getLanguage().equals(language)) {
                magicCards.add(fromEntity(magicCardEntity));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findBySetType(SetType setType) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : findAllEntities()) {
            if (magicCardEntity.getSetType().equals(setType)) {
                magicCards.add(fromEntity(magicCardEntity));
            }
        }
        return magicCards;
    }

    public List<MagicCardDto> findBySetName(String setName) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : findAllEntities()) {
            if (magicCardEntity.getSetName().equals(setName)) {
                magicCards.add(fromEntity(magicCardEntity));
            }
        }
        return magicCards;
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


        List<MagicCardDto> magicCards = new ArrayList<>();
//        for (MagicCardEntity magicCardEntity : findAllEntities()) {
//            if (magicCardEntity.getName().toLowerCase().contains(term.toLowerCase())) {
//                magicCards.add(fromEntity(magicCardEntity));
//            }
//        }
        return magicCards;
    }

    /////////// Paging
    public PagingResult<MagicCardDto> findOnePageDto(int pageIndex, int pageSize) {
        List<MagicCardEntity> magicCardEntities = findPaged(pageIndex, pageSize);
        return new PagingResult<>(fromEntity(magicCardEntities), getSize());

//        List<MagicCardEntity> magicCardEntities = findAllEntities();
//        int begin = pageSize * (pageIndex);
//        int end = pageSize * (pageIndex) + pageSize;
//        if (begin >= magicCardEntities.size() || begin < 0) {
//            System.out.println("Index out of bounds: begin=" + begin + ", end=" + end + ", size=" + magicCardEntities.size());
//            begin = 0;
//            end = pageSize;
//        } else if (end >= magicCardEntities.size()) {
//            end = magicCardEntities.size();
//        }
//        System.out.println("begin=" + begin + ", end=" + end + ", size=" + magicCardEntities.size());
//        return new PagingResult<>(fromEntity(magicCardEntities.subList(begin, end)), magicCardEntities.size());
    }

}
