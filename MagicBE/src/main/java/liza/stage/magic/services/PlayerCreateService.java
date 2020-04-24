package liza.stage.magic.services;

import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.players.entities.DeckEntity;
import liza.stage.magic.models.players.entities.MainCollectionEntity;
import liza.stage.magic.models.players.entities.PlayerEntity;
import liza.stage.magic.repositories.DeckRepository;
import liza.stage.magic.repositories.MainCollectionRepository;
import liza.stage.magic.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerCreateService {
    private final MagicCardService magicCardService;
    private final PlayerRepository playerRepository;
    private final MainCollectionRepository mainCollectionRepository;
    private final DeckRepository deckRepository;

    public void create() {
        Pageable pageableLiza = PageRequest.of(1, 30);
        List<MagicCardEntity> collectionLizaCards = new ArrayList<>(magicCardService.findCardsPageEntity(pageableLiza).data);
        List<MagicCardEntity> collectionLizaDeck1 = new ArrayList<>(collectionLizaCards.subList(0,10));
        List<MagicCardEntity> collectionLizaDeck2 = new ArrayList<>(collectionLizaCards.subList(6,13));

        MainCollectionEntity collectionLiza = new MainCollectionEntity();
        collectionLiza.setMagicCards(collectionLizaCards);

        DeckEntity deck1Liza = new DeckEntity();
        deck1Liza.setDeckName("Deck 1");
        deck1Liza.setMagicCards(collectionLizaDeck1);
        DeckEntity deck2Liza = new DeckEntity();
        deck2Liza.setDeckName("Naamloos deck");
        deck2Liza.setMagicCards(collectionLizaDeck2);

        List<DeckEntity> decksLiza = new ArrayList<>();
        decksLiza.add(deck1Liza);
        decksLiza.add(deck2Liza);
        
        PlayerEntity playerLiza = new PlayerEntity();
        playerLiza.setName("Liza");
        playerLiza.setMainCollection(collectionLiza);
        playerLiza.setDecks(decksLiza);



        Pageable pageableStefan = PageRequest.of(4, 20);
        List<MagicCardEntity> collectionStefanCards = new ArrayList<>(magicCardService.findCardsPageEntity(pageableStefan).data);
        List<MagicCardEntity> collectionStefanDeck1 = new ArrayList<>(collectionStefanCards.subList(0,10));
        List<MagicCardEntity> collectionStefanDeck2 = new ArrayList<>(collectionStefanCards.subList(6,13));

        MainCollectionEntity collectionStefan = new MainCollectionEntity();
        collectionStefan.setMagicCards(collectionStefanCards);

        DeckEntity deck1Stefan = new DeckEntity();
        deck1Stefan.setDeckName("Winnend deck");
        deck1Stefan.setMagicCards(collectionStefanDeck1);
        DeckEntity deck2Stefan = new DeckEntity();
        deck2Stefan.setDeckName("Geweldig deck");
        deck2Stefan.setMagicCards(collectionStefanDeck2);

        List<DeckEntity> decksStefan = new ArrayList<>();
        decksStefan.add(deck1Stefan);
        decksStefan.add(deck2Stefan);

        PlayerEntity playerStefan = new PlayerEntity();
        playerStefan.setName("Stefan");
        playerStefan.setMainCollection(collectionStefan);
        playerStefan.setDecks(decksStefan);


        Pageable pageableSven = PageRequest.of(2, 40);
        List<MagicCardEntity> collectionSvenCards = new ArrayList<>(magicCardService.findCardsPageEntity(pageableSven).data);
        List<MagicCardEntity> collectionSvenDeck1 = new ArrayList<>(collectionSvenCards.subList(0,10));
        List<MagicCardEntity> collectionSvenDeck2 = new ArrayList<>(collectionSvenCards.subList(6,13));

        MainCollectionEntity collectionSven = new MainCollectionEntity();
        collectionSven.setMagicCards(collectionSvenCards);

        DeckEntity deck1Sven = new DeckEntity();
        deck1Sven.setDeckName("Deck 1");
        deck1Sven.setMagicCards(collectionSvenDeck1);
        DeckEntity deck2Sven = new DeckEntity();
        deck2Sven.setDeckName("Deck 2");
        deck2Sven.setMagicCards(collectionSvenDeck2);

        List<DeckEntity> decksSven = new ArrayList<>();
        decksSven.add(deck1Sven);
        decksSven.add(deck2Sven);

        PlayerEntity playerSven = new PlayerEntity();
        playerSven.setName("Sven");
        playerSven.setMainCollection(collectionSven);
        playerSven.setDecks(decksSven);

        mainCollectionRepository.save(collectionLiza);
        deckRepository.save(deck1Liza);
        deckRepository.save(deck2Liza);
        playerRepository.save(playerLiza);
        mainCollectionRepository.save(collectionStefan);
        deckRepository.save(deck1Stefan);
        deckRepository.save(deck2Stefan);
        playerRepository.save(playerStefan);
        mainCollectionRepository.save(collectionSven);
        deckRepository.save(deck1Sven);
        deckRepository.save(deck2Sven);
        playerRepository.save(playerSven);
    }

}
