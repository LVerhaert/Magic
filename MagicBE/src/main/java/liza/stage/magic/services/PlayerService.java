package liza.stage.magic.services;

import liza.stage.magic.mappers.players.CollectionMapper;
import liza.stage.magic.mappers.players.PlayerMapper;
import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.models.magiccards.dtos.PagingResult;
import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.players.dtos.DeckDto;
import liza.stage.magic.models.players.dtos.MainCollectionDto;
import liza.stage.magic.models.players.dtos.PlayerDto;
import liza.stage.magic.models.players.entities.DeckEntity;
import liza.stage.magic.models.players.entities.MainCollectionEntity;
import liza.stage.magic.models.players.entities.PlayerEntity;
import liza.stage.magic.repositories.DeckEntitiesRepository;
import liza.stage.magic.repositories.MainCollectionEntitiesRepository;
import liza.stage.magic.repositories.PlayerEntitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerEntitiesRepository playerEntitiesRepository;
    private final MainCollectionEntitiesRepository mainCollectionEntitiesRepository;
    private final DeckEntitiesRepository deckEntitiesRepository;
    private final MagicCardService magicCardService;

    private final PlayerMapper playerMapper;
    private final CollectionMapper collectionMapper;

    /////////////// Find
    ///// Entities
    public PlayerEntity findPlayerById(int playerId) {
        return playerEntitiesRepository.findById(playerId).orElse(null);
    }

    public DeckEntity findDeckById(int deckId) {
        return deckEntitiesRepository.findById(deckId).orElse(null);
    }

    public Collection<DeckEntity> findDecksById(List<Integer> deckIds) {
        return (Collection<DeckEntity>) deckEntitiesRepository.findAllById(deckIds);
    }

    private MainCollectionEntity findMainCollectionById(int mainCollectionId) {
        return mainCollectionEntitiesRepository.findById(mainCollectionId).orElse(null);
    }

    private MagicCardEntity findCardById(String cardId) {
        return magicCardService.findEntityById(cardId);
    }

    private List<MagicCardEntity> findCardsById(List<String> cardIds, Pageable pageable) {
        return magicCardService.findEntitiesById(cardIds, pageable);
    }

    private List<MagicCardEntity> findCardsById(List<String> cardIds) {
        return magicCardService.findEntitiesById(cardIds);
    }

    ////// DTO's
    public PlayerDto findPlayer(int playerId) {
        return entityToDto(findPlayerById(playerId));
    }

    public List<DeckDto> findDecks(int playerId) {
        PlayerEntity playerEntity = findPlayerById(playerId);
        return entityToDto(playerEntity.getDecks());
    }

    public DeckDto findDeck(int playerId, int deckId) {
        PlayerEntity playerEntity = findPlayerById(playerId);
        DeckEntity deckEntity = findDeckById(deckId);
        if (playerEntity.getDecks().contains(deckEntity)) {
            return entityToDto(deckEntity);
        }
        return null;
    }

    public MainCollectionDto findMainCollection(int playerId) {
        PlayerEntity playerEntity = findPlayerById(playerId);
        return entityToDto(playerEntity.getMainCollection());
    }


    ///////////////// Mappers
    ///// Entity -> DTO
    private PlayerDto entityToDto(PlayerEntity playerEntity) {
        return playerMapper.toDto(playerEntity);
    }

    private MainCollectionDto entityToDto(MainCollectionEntity mainCollectionEntity) {
        return collectionMapper.toDto(mainCollectionEntity);
    }

    private DeckDto entityToDto(DeckEntity deckEntity) {
        return collectionMapper.toDto(deckEntity);
    }

    private List<DeckDto> entityToDto(List<DeckEntity> deckEntities) {
        ArrayList<DeckDto> deckDtos = new ArrayList<>();
        for (DeckEntity deckEntity : deckEntities) {
            deckDtos.add(collectionMapper.toDto(deckEntity));
        }
        return deckDtos;
    }

    ///// DTO -> Entity
    private PlayerEntity dtoToEntity(PlayerDto playerDto) {
        return playerMapper.toEntity(playerDto);
    }

    private DeckEntity dtoToEntity(DeckDto deckDto) {
        return collectionMapper.toEntity(deckDto);
    }


    /////////// Operations
    ///// Add
    public void addPlayer(String name) {
        // create player
        PlayerEntity newPlayer = dtoToEntity(new PlayerDto(name));
        playerEntitiesRepository.save(newPlayer);
    }

    public void addDeck(int playerId, String name) {
        // create deck
        DeckEntity newDeck = dtoToEntity(new DeckDto(name));
        deckEntitiesRepository.save(newDeck);
        // add deck to player
        PlayerEntity player = findPlayerById(playerId);
        player.getDecks().add(newDeck);
        playerEntitiesRepository.save(player);
    }

    public void addCardToDeck(int deckId, String cardId) {
        // add card to deck
        DeckEntity deck = findDeckById(deckId);
        MagicCardEntity magicCard = findCardById(cardId);
        deck.getMagicCards().add(magicCard);
        deckEntitiesRepository.save(deck);
    }

    public void addCardToMainCollection(int mainCollectionId, String cardId) {
        // add card to main collection
        MainCollectionEntity mainCollection = findMainCollectionById(mainCollectionId);
        MagicCardEntity magicCard = findCardById(cardId);
        mainCollection.getMagicCards().add(magicCard);
        mainCollectionEntitiesRepository.save(mainCollection);
    }

    public void addCardsToDeck(int deckId, List<String> cardIds) {
        // add cards to deck
        DeckEntity deck = findDeckById(deckId);
        Collection<MagicCardEntity> magicCards = findCardsById(cardIds);
        deck.getMagicCards().addAll(magicCards);
        deckEntitiesRepository.save(deck);
    }

    public void addCardsToMainCollection(int mainCollectionId, List<String> cardIds) {
        // add cards to main collection
        MainCollectionEntity mainCollection = findMainCollectionById(mainCollectionId);
        Collection<MagicCardEntity> magicCards = findCardsById(cardIds);
        mainCollection.getMagicCards().addAll(magicCards);
        mainCollectionEntitiesRepository.save(mainCollection);
    }

    ///// Remove
    public void removePlayer(int playerId) {
        PlayerEntity player = findPlayerById(playerId);
        // delete player's decks
        deckEntitiesRepository.deleteAll(player.getDecks());
        // delete player's main collection
        mainCollectionEntitiesRepository.delete(player.getMainCollection());
        // delete player
        playerEntitiesRepository.delete(player);
    }

    public void removeDeck(int playerId, int deckId) {
        DeckEntity deck = findDeckById(deckId);
        // delete deck from player
        PlayerEntity player = findPlayerById(playerId);
        player.getDecks().remove(deck);
        playerEntitiesRepository.save(player);
        // delete deck
        deckEntitiesRepository.delete(deck);
    }

    public void removeDecks(int playerId, List<Integer> deckIds) {
        Collection<DeckEntity> decks = findDecksById(deckIds);
        // delete deck from player
        PlayerEntity player = findPlayerById(playerId);
        player.getDecks().removeAll(decks);
        playerEntitiesRepository.save(player);
        // delete deck
        deckEntitiesRepository.deleteAll(decks);
    }

    public void removeCardFromDeck(int deckId, String cardId) {
        DeckEntity deck = findDeckById(deckId);
        deck.getMagicCards().remove(findCardById(cardId));
        deckEntitiesRepository.save(deck);
    }

    public void removeCardFromMainCollection(int mainCollectionId, String cardId) {
        MainCollectionEntity mainCollection = findMainCollectionById(mainCollectionId);
        mainCollection.getMagicCards().remove(findCardById(cardId));
        mainCollectionEntitiesRepository.save(mainCollection);
    }

    public void removeCardsFromDeck(int deckId, List<String> cardIds) {
        DeckEntity deck = findDeckById(deckId);
        deck.getMagicCards().removeAll(findCardsById(cardIds));
        deckEntitiesRepository.save(deck);
    }

    public void removeCardsFromMainCollection(int mainCollectionId, List<String> cardIds) {
        MainCollectionEntity mainCollection = findMainCollectionById(mainCollectionId);
        mainCollection.getMagicCards().removeAll(findCardsById(cardIds));
        mainCollectionEntitiesRepository.save(mainCollection);
    }

    ///// Update
    public void changePlayerName(int playerId, String name) {
        PlayerEntity player = findPlayerById(playerId);
        player.setName(name);
        playerEntitiesRepository.save(player);
    }

    public void changeDeckName(int deckId, String name) {
        DeckEntity deck = findDeckById(deckId);
        deck.setDeckName(name);
        deckEntitiesRepository.save(deck);
    }


    ////////// Paging
    public PagingResult<MagicCardDto> findDeckOnePage(int playerId, int deckId, Pageable pageable) {
        DeckDto deck = findDeck(playerId, deckId);
        return new PagingResult<>(deck.getMagicCardsPaged(pageable), deck.getMagicCards().size());
    }

    public PagingResult<MagicCardDto> findCollectionOnePage(int playerId, Pageable pageable) {
        MainCollectionDto mainCollection = findMainCollection(playerId);
        return new PagingResult<>(mainCollection.getMagicCardsPaged(pageable), mainCollection.getMagicCards().size());
    }

}
