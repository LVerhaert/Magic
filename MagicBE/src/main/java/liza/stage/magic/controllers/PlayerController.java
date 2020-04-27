package liza.stage.magic.controllers;

import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.models.players.dtos.DeckDto;
import liza.stage.magic.models.players.dtos.MainCollectionDto;
import liza.stage.magic.models.players.dtos.PlayerDto;
import liza.stage.magic.services.OnePageResult;
import liza.stage.magic.services.PlayerService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/players")
    public OnePageResult<PlayerDto> getPlayersOnePage(@RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                      @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/players?pageIndex=" + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findPlayersPage(pageable);
    }

    @GetMapping("/player/{playerid}")
    public PlayerDto getPlayer(@PathVariable("playerid") String playerId) {
        System.out.println("localhost:8080/player/" + playerId + " called");
        return playerService.findPlayer(Integer.parseInt(playerId));
    }

    @GetMapping("/player/{playerid}/deck/{deckid}/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getDeckCardsOnePage(@PathVariable("playerid") String playerId,
                                                           @PathVariable("deckid") String deckId,
                                                           @RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                           @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/player/" + playerId + "/deck/" + deckId + "magiccards?pageIndex="
                + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findDeckCardsPage(Integer.parseInt(playerId), Integer.parseInt(deckId), pageable);
    }

    @GetMapping("/player/{playerid}/maincollection/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getMainCollectionCardsOnePage(@PathVariable("playerid") String playerId,
                                                                     @RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/player/" + playerId + "/maincollection/magiccards?pageIndex="
                + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return playerService.findCollectionCardsPage(Integer.parseInt(playerId), pageable);
    }

}
