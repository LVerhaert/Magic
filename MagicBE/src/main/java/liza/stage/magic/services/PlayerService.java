package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.CollectionMapper;
import liza.stage.magic.mappers.dtomappers.PlayerMapper;
import liza.stage.magic.models.magiccards.magiccarddtos.MagicCardDto;
import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import liza.stage.magic.models.players.playerdtos.DeckDto;
import liza.stage.magic.models.players.playerdtos.MainCollectionDto;
import liza.stage.magic.models.players.playerdtos.PlayerDto;
import liza.stage.magic.models.players.playerentities.DeckEntity;
import liza.stage.magic.models.players.playerentities.MainCollectionEntity;
import liza.stage.magic.models.players.playerentities.PlayerEntity;
import liza.stage.magic.repositories.playerrepositories.DeckRepository;
import liza.stage.magic.repositories.playerrepositories.MainCollectionRepository;
import liza.stage.magic.repositories.playerrepositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MainCollectionRepository mainCollectionRepository;
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private MagicCardService magicCardService;

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    /////////////// Find
    ///// Entities
    private PlayerEntity findPlayerEntityById(int playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    private DeckEntity findDeckEntityById(int deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }

    private DeckEntity findDeckEntity(int playerId, int deckId) {
        DeckEntity deckEntity = deckRepository.findById(deckId).orElse(null);
        PlayerEntity playerEntity = playerRepository.findById(playerId).orElse(null);
        if (deckEntity != null && playerEntity != null) {
            if (playerEntity.getDecks().contains(deckEntity)) {
                return deckEntity;
            }
        }
        return null;
    }

    private MainCollectionEntity findMainCollectionEntityById(int mainCollectionId) {
        return mainCollectionRepository.findById(mainCollectionId).orElse(null);
    }

    private MagicCardEntity findMagicCardEntityById(String cardId) {
        return magicCardService.findEntityById(cardId);
    }

    private List<MagicCardEntity> findMagicCardEntitiesById(List<String> cardIds, Pageable pageable) {
        return magicCardService.findEntitiesById(cardIds, pageable);
    }

    private List<MagicCardEntity> findMagicCardEntitiesById(List<String> cardIds) {
        return magicCardService.findEntitiesById(cardIds);
    }

    ////// DTO's
    public PlayerDto findPlayer(int playerId) {
        return playerMapper.map(findPlayerEntityById(playerId));
    }

    public OnePageResult<PlayerDto> findPlayers(Pageable pageable) {
        List<PlayerEntity> players = playerRepository.findAll(pageable).getContent();
        return new OnePageResult<>(this.playerEntitiesToDtos(players), playerRepository.count());
    }

    public MainCollectionDto findMainCollection(int playerId) {
        PlayerEntity playerEntity = findPlayerEntityById(playerId);
        return collectionMapper.map(playerEntity.getMainCollection());
    }

    public DeckDto findDeck(int playerId, int deckId) {
        return collectionMapper.map(findDeckEntity(playerId, deckId));
    }

    public OnePageResult<DeckDto> findDecks(int playerId, Pageable pageable) {
        PlayerEntity player = playerRepository.findById(playerId).orElse(null);
        List<DeckDto> decks = new ArrayList<>();
        if (player != null) {
            decks = deckEntitiesToDtos(deckRepository.findByPlayer(player, pageable));
        }
        return new OnePageResult<>(decks, decks.size());
    }

    ////////// Entity -> DTO
    private List<PlayerDto> playerEntitiesToDtos(List<PlayerEntity> playerEntities) {
        List<PlayerDto> players = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            players.add(playerMapper.map(playerEntity));
        }
        return players;
    }

    private List<DeckDto> deckEntitiesToDtos(List<DeckEntity> deckEntities) {
        List<DeckDto> decks = new ArrayList<>();
        for (DeckEntity deckEntity : deckEntities) {
            decks.add(collectionMapper.map(deckEntity));
        }
        return decks;
    }

    ////////// MagicCards
    public OnePageResult<MagicCardDto> findDeckCards(int playerId, int deckId, Pageable pageable) {
        List<String> magicCardIds = findDeck(playerId, deckId).getMagicCardIds();
        List<MagicCardEntity> magicCardEntities = findMagicCardEntitiesById(magicCardIds, pageable);
        List<MagicCardDto> magicCards = magicCardService.entityToDto(magicCardEntities);
        int size = magicCardIds.size();
        return new OnePageResult<>(magicCards, size);
    }

    public OnePageResult<MagicCardDto> findMainCollectionCards(int playerId, Pageable pageable) {
        List<String> magicCardIds = findMainCollection(playerId).getMagicCardIds();
        List<MagicCardEntity> magicCardEntities = findMagicCardEntitiesById(magicCardIds, pageable);
        List<MagicCardDto> magicCards = magicCardService.entityToDto(magicCardEntities);
        int size = magicCardIds.size();
        return new OnePageResult<>(magicCards, size);
    }

    /////////// Operations
    ///// Add
    public void addPlayer(String name) {
        // create player
        PlayerEntity newPlayer = playerMapper.map(new PlayerDto(name));
        playerRepository.save(newPlayer);
    }

    public void addDeck(int playerId, String name) {
        // create deck
        DeckEntity newDeck = collectionMapper.map(new DeckDto(name));
        deckRepository.save(newDeck);
        // add deck to player
        PlayerEntity player = findPlayerEntityById(playerId);
        player.getDecks().add(newDeck);
        playerRepository.save(player);
    }

    public void addCardToDeck(int deckId, String cardId) {
        // add card to deck
        DeckEntity deck = findDeckEntityById(deckId);
        MagicCardEntity magicCard = findMagicCardEntityById(cardId);
        deck.getMagicCards().add(magicCard);
        deckRepository.save(deck);
    }

    public void addCardToMainCollection(int mainCollectionId, String cardId) {
        // add card to main collection
        MainCollectionEntity mainCollection = findMainCollectionEntityById(mainCollectionId);
        MagicCardEntity magicCard = findMagicCardEntityById(cardId);
        mainCollection.getMagicCards().add(magicCard);
        mainCollectionRepository.save(mainCollection);
    }

    public void addCardsToDeck(int deckId, List<String> cardIds) {
        // add cards to deck
        DeckEntity deck = findDeckEntityById(deckId);
        deck.getMagicCards().addAll(findMagicCardEntitiesById(cardIds));
        deckRepository.save(deck);
    }

    public void addCardsToMainCollection(int mainCollectionId, List<String> cardIds) {
        // add cards to main collection
        MainCollectionEntity mainCollection = findMainCollectionEntityById(mainCollectionId);
        mainCollection.getMagicCards().addAll(findMagicCardEntitiesById(cardIds));
        mainCollectionRepository.save(mainCollection);
    }

    ///// Remove
    public void removePlayer(int playerId) {
        PlayerEntity player = findPlayerEntityById(playerId);
        // delete player's decks
        deckRepository.deleteAll(player.getDecks());
        // delete player's main collection
        mainCollectionRepository.delete(player.getMainCollection());
        // delete player
        playerRepository.delete(player);
    }

    public void removeDeck(int playerId, int deckId) {
        DeckEntity deck = findDeckEntityById(deckId);
        // delete deck from player
        PlayerEntity player = findPlayerEntityById(playerId);
        player.getDecks().remove(deck);
        playerRepository.save(player);
        // delete deck
        deckRepository.delete(deck);
    }

    public void removeDecks(int playerId, List<Integer> deckIds) {
        List<DeckEntity> decks = deckRepository.findAllById(deckIds);
        // delete deck from player
        PlayerEntity player = findPlayerEntityById(playerId);
        player.getDecks().removeAll(decks);
        playerRepository.save(player);
        // delete deck
        deckRepository.deleteAll(decks);
    }

    public void removeCardFromDeck(int deckId, String cardId) {
        DeckEntity deck = findDeckEntityById(deckId);
        deck.getMagicCards().remove(findMagicCardEntityById(cardId));
        deckRepository.save(deck);
    }

    public void removeCardFromMainCollection(int mainCollectionId, String cardId) {
        MainCollectionEntity mainCollection = findMainCollectionEntityById(mainCollectionId);
        mainCollection.getMagicCards().remove(findMagicCardEntityById(cardId));
        mainCollectionRepository.save(mainCollection);
    }

    public void removeCardsFromDeck(int deckId, List<String> cardIds) {
        DeckEntity deck = findDeckEntityById(deckId);
        deck.getMagicCards().removeAll(findMagicCardEntitiesById(cardIds));
        deckRepository.save(deck);
    }

    public void removeCardsFromMainCollection(int mainCollectionId, List<String> cardIds) {
        MainCollectionEntity mainCollection = findMainCollectionEntityById(mainCollectionId);
        mainCollection.getMagicCards().removeAll(findMagicCardEntitiesById(cardIds));
        mainCollectionRepository.save(mainCollection);
    }

    ///// Update
    public void changePlayerName(int playerId, String name) {
        PlayerEntity player = findPlayerEntityById(playerId);
        player.setName(name);
        playerRepository.save(player);
    }

    public void changeDeckName(int deckId, String name) {
        DeckEntity deck = findDeckEntityById(deckId);
        deck.setDeckName(name);
        deckRepository.save(deck);
    }


}
