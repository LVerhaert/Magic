package liza.stage.magic.controllers;

import liza.stage.magic.models.magiccards.magiccarddtos.MagicCardDto;
import liza.stage.magic.models.players.playerdtos.DeckDto;
import liza.stage.magic.models.players.playerdtos.PlayerDto;
import liza.stage.magic.services.OnePageResult;
import liza.stage.magic.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public OnePageResult<PlayerDto> getPlayers(@RequestParam(required = false, defaultValue = "0") String pageIndex,
                                               @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/players?pageIndex=" + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findPlayers(pageable);
    }

    @GetMapping("/player/{playerid}")
    public PlayerDto getPlayer(@PathVariable("playerid") String playerId) {
        System.out.println("localhost:8080/player/" + playerId + " called");
        return playerService.findPlayer(Integer.parseInt(playerId));
    }

    @GetMapping("/player/{playerid}/deck/{deckid}/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getDeckCards(@PathVariable("playerid") String playerId,
                                                    @PathVariable("deckid") String deckId,
                                                    @RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                    @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/player/" + playerId + "/deck/" + deckId + "magiccards?pageIndex="
                + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findDeckCards(Integer.parseInt(playerId), Integer.parseInt(deckId), pageable);
    }

    @GetMapping("/player/{playerid}/maincollection/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getMainCollectionCards(@PathVariable("playerid") String playerId,
                                                              @RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                              @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/player/" + playerId + "/maincollection/magiccards?pageIndex="
                + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findMainCollectionCards(Integer.parseInt(playerId), pageable);
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
        return playerService.findDecks(Integer.parseInt(playerId), pageable);
    }

}
