package liza.stage.magic.controllers;

import liza.stage.magic.models.magiccards.magiccarddtos.MagicCardDto;
import liza.stage.magic.models.players.playerdtos.DeckDto;
import liza.stage.magic.models.players.playerdtos.MainCollectionDto;
import liza.stage.magic.services.MagicCardService;
import liza.stage.magic.services.OnePageResult;
import liza.stage.magic.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardsController {
    @Autowired
    private MagicCardService magicCardService;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getMagicCards(@RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                     @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/magiccards?pageIndex=" + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return magicCardService.findCards(pageable);
    }

    /*
    @GetMapping("magiccard/{id}/related")
    public Map<MagicCardDto, Relationship> getRelatedCards(@PathVariable("id") String id) {
        System.out.println("localhost:8080/magiccard/" + id + "/related called");
        return magicCardService.findAllRelatedTo(id);
    }
     */

    @GetMapping("/magiccard/{id}")
    public MagicCardDto getMagicCard(@PathVariable("id") String id) {
        System.out.println("localhost:8080/magiccard/" + id + " called");
        return magicCardService.findCardById(id);
    }

    @GetMapping("/magiccards/search")
    @ResponseBody
    public List<MagicCardDto> searchMagicCard(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) String playerId,
                                              @RequestParam(required = false) String deckId) {
        System.out.println("localhost:8080/magiccards/search?name=" + name + "&playerId=" + playerId + "&deckId=" + deckId + " called");
        if (name == null) {
            return new ArrayList<>();
        } else if (playerId == null) {
            return magicCardService.searchByName(name);
        } else if (deckId == null) {
            MainCollectionDto mainCollection = playerService.findMainCollection(Integer.parseInt(playerId));
            return magicCardService.searchListByName(mainCollection.getMagicCardIds(), name);
        } else {
            DeckDto deck = playerService.findDeck(Integer.parseInt(playerId), Integer.parseInt(deckId));
            return magicCardService.searchListByName(deck.getMagicCardIds(), name);
        }
    }
}
