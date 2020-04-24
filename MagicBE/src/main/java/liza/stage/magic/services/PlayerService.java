package liza.stage.magic.services;

import liza.stage.magic.mappers.dtomappers.CollectionMapper;
import liza.stage.magic.mappers.dtomappers.PlayerMapper;
import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.players.dtos.*;
import liza.stage.magic.models.players.entities.*;
import liza.stage.magic.repositories.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final MainCollectionRepository mainCollectionRepository;
    private final DeckRepository deckRepository;
    private final MagicCardService magicCardService;

    private final PlayerMapper playerMapper;
    private final CollectionMapper collectionMapper;

    /////////////// Find
    ///// Entities
    public PlayerEntity findPlayerEntityById(int playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    public DeckEntity findDeckEntityById(int deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }

    private MainCollectionEntity findMainCollectionEntityById(int mainCollectionId) {
        return mainCollectionRepository.findById(mainCollectionId).orElse(null);
    }

    private MagicCardEntity findMagicCardEntityById(String cardId) {
        return magicCardService.findEntityById(cardId);
    }

    private List<MagicCardEntity> findMagicCardEntitiesById(List<String> cardIds) {
        return magicCardService.findEntitiesById(cardIds);
    }

    ////// DTO's
    public PlayerDto findPlayer(int playerId) {
        return playerMapper.toDto(findPlayerEntityById(playerId));
    }


    public List<PlayerDto> findPlayers() {
        List<PlayerDto> players = new ArrayList<>();
        for (PlayerEntity player : playerRepository.findAll()) {
            players.add(playerMapper.toDto(player));
        }
        return players;
    }

    public List<DeckDto> findDecks(int playerId) {
        PlayerEntity playerEntity = findPlayerEntityById(playerId);
        ArrayList<DeckDto> deckDtos = new ArrayList<>();
        for (DeckEntity deckEntity : playerEntity.getDecks()) {
            deckDtos.add(collectionMapper.toDto(deckEntity));
        }
        return deckDtos;
    }

    public DeckDto findDeck(int playerId, int deckId) {
        PlayerEntity playerEntity = findPlayerEntityById(playerId);
        DeckEntity deckEntity = findDeckEntityById(deckId);
        if (playerEntity.getDecks().contains(deckEntity)) {
            return collectionMapper.toDto(deckEntity);
        }
        return null;
    }

    public MainCollectionDto findMainCollection(int playerId) {
        PlayerEntity playerEntity = findPlayerEntityById(playerId);
        return collectionMapper.toDto(playerEntity.getMainCollection());
    }


    /////////// Operations
    ///// Add
    public void addPlayer(String name) {
        // create player
        PlayerEntity newPlayer = playerMapper.toEntity(new PlayerDto(name));
        playerRepository.save(newPlayer);
    }

    public void addDeck(int playerId, String name) {
        // create deck
        DeckEntity newDeck = collectionMapper.toEntity(new DeckDto(name));
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


    ////////// Paging
    public OnePageResult<MagicCardDto> findDeckCardsPage(int playerId, int deckId, Pageable pageable) {
        DeckDto deck = findDeck(playerId, deckId);
        List<MagicCardDto> magicCards = deck.getMagicCardsPaged(pageable);
        int size = deck.getMagicCards().size();
        return new OnePageResult<>(magicCards, size);
    }

    public OnePageResult<MagicCardDto> findCollectionCardsPage(int playerId, Pageable pageable) {
        MainCollectionDto mainCollection = findMainCollection(playerId);
        List<MagicCardDto> magicCards = mainCollection.getMagicCardsPaged(pageable);
        int size = mainCollection.getMagicCards().size();
        return new OnePageResult<>(magicCards, size);
    }

    public OnePageResult<PlayerDto> findPlayersPage(Pageable pageable) {
        List<PlayerEntity> players = playerRepository.findAll(pageable).getContent();
        return new OnePageResult<>(entityToDto(players), playerRepository.count());
    }

    private List<PlayerDto> entityToDto(List<PlayerEntity> playerEntities) {
        List<PlayerDto> players = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            players.add(playerMapper.toDto(playerEntity));
        }
        return players;
    }

    public OnePageResult<DeckDto> findDecksPage(int playerId, Pageable pageable) {
        List<DeckDto> decks = findDecks(playerId);
        return new OnePageResult<>(decks, decks.size());
    }
}
