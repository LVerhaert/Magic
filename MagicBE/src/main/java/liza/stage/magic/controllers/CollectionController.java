package liza.stage.magic.controllers;

import liza.stage.magic.models.players.dtos.DeckDto;
import liza.stage.magic.models.players.dtos.MainCollectionDto;
import liza.stage.magic.services.OnePageResult;
import liza.stage.magic.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CollectionController {
    private final PlayerService playerService;

    @GetMapping("/player/{playerid}/maincollection")
    public MainCollectionDto getMainCollection(@PathVariable("playerid") String playerId) {
        System.out.println("localhost:8080/player/" + playerId + "/maincollection called");
        return playerService.findMainCollection(Integer.parseInt(playerId));
    }

    @GetMapping("/player/{playerid}/deck/{deckid}")
    public DeckDto getDeck(@PathVariable("playerid") String playerId, @PathVariable("deckid") String deckId) {
        System.out.println("localhost:8080/player/" + playerId + "/deck/" + deckId + " called");
        return playerService.findDeck(Integer.parseInt(playerId), Integer.parseInt(deckId));
    }

    @GetMapping("/player/{playerid}/decks")
    public OnePageResult<DeckDto> getDecks(@PathVariable("playerid") String playerId,
                                           @RequestParam(required = false, defaultValue = "0") String pageIndex,
                                           @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/player/" + playerId + "/decks?pageIndex="
                + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findDecksPage(Integer.parseInt(playerId), pageable);
    }
}
