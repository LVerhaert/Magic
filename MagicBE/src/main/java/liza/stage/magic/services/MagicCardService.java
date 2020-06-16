package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.MagicCardMapper;
import liza.stage.magic.models.magiccards.magiccarddtos.MagicCardDto;
import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import liza.stage.magic.repositories.MagicCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import liza.stage.magic.models.magiccards.entities.RelatedCardEntity;
//import liza.stage.magic.models.magiccards.enums.Relationship;
//import liza.stage.magic.repositories.RelatedCardRepository;

@Service
public class MagicCardService {
    @Autowired
    private MagicCardRepository magicCardRepository;
    //    @Autowired
    //    private RelatedCardRepository relatedCardRepository;
    @Autowired
    private MagicCardMapper magicCardMapper;

    ////////////// Entities
    public MagicCardEntity findEntityById(String id) {
        return magicCardRepository.findById(id).orElse(null);
    }

    public List<MagicCardEntity> findEntitiesById(List<String> ids, Pageable pageable) {
        return magicCardRepository.findAllByScryfallIdIn(ids, pageable);
    }

    public List<MagicCardEntity> findEntitiesById(List<String> ids) {
        return magicCardRepository.findAllById(ids);
    }

    //////////////// Entity -> DTO
    public List<MagicCardDto> entityToDto(List<MagicCardEntity> magicCardEntities) {
        List<MagicCardDto> magicCards = new ArrayList<>();
        for (MagicCardEntity magicCardEntity : magicCardEntities) {
            magicCards.add(entityToDto(magicCardEntity));
        }
        return magicCards;
    }

    private MagicCardDto entityToDto(MagicCardEntity magicCardEntity) {
        MagicCardDto magicCardDto = magicCardMapper.map(magicCardEntity);
        if (magicCardDto.getCardFaces().size() > 0) {
            magicCardDto.setSmallImageUri(magicCardDto.getCardFaces().get(0).getSmallImageUri());
            magicCardDto.setLargeImageUri(magicCardDto.getCardFaces().get(0).getLargeImageUri());
        }
        return magicCardDto;
    }

    ////////////// DTO
    public MagicCardDto findCardById(String id) {
        return entityToDto(magicCardRepository.findById(id).orElse(null));
    }

//    public Map<MagicCardDto, Relationship> findAllRelatedTo(String id) {
//        MagicCardDto magicCard = findById(id);
//        Map<String, Relationship> relatedCards = magicCard.getRelatedCards();
//        Map<MagicCardDto, Relationship> related = new HashMap<>();
//        if (relatedCards != null) {
//            for (Map.Entry<String, Relationship> relatedCard : relatedCards.entrySet()) {
//                related.put(findById(relatedCard.getKey()), relatedCard.getValue());
//            }
//        }
//        return related;
//    }

    ////////////// Search
    public List<MagicCardDto> searchByName(String term) {
        List<MagicCardEntity> magicCards = magicCardRepository.findTop10ByNameContainsIgnoreCase(term);
        return entityToDto(magicCards);
    }

    public List<MagicCardDto> searchListByName(List<String> ids, String term) {
        List<MagicCardEntity> magicCards = magicCardRepository.findTop10ByScryfallIdInAndNameContainsIgnoreCase(ids, term);
        return entityToDto(magicCards);
    }

    /////////// Paging
    public OnePageResult<MagicCardDto> findCards(Pageable pageable) {
        List<MagicCardEntity> magicCards = magicCardRepository.findAll(pageable).getContent();
        return new OnePageResult<>(entityToDto(magicCards), magicCardRepository.count());
    }

    public OnePageResult<MagicCardEntity> findCardsEntity(Pageable pageable) {
        List<MagicCardEntity> magicCards = magicCardRepository.findAll(pageable).getContent();
        return new OnePageResult<>(magicCards, magicCardRepository.count());
    }

}
