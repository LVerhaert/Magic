package liza.stage.magic.controllers;

import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.models.magiccards.enums.Relationship;
import liza.stage.magic.services.MagicCardService;
import liza.stage.magic.services.OnePageResult;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardsController {
    private final MagicCardService magicCardService;

    @GetMapping("/magiccards")
    @ResponseBody
    public OnePageResult<MagicCardDto> getMagicCardsOnePage(@RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                            @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/magiccards?pageIndex=" + pageIndex + ", pageSize=" + pageSize + " called");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return magicCardService.findCardsPage(pageable);
    }

    @GetMapping("magiccard/{id}/related")
    public Map<MagicCardDto, Relationship> getRelatedCards(@PathVariable("id") String id) {
        System.out.println("localhost:8080/magiccard/" + id + "/related called");
        return magicCardService.findAllRelatedTo(id);
    }

    @GetMapping("/magiccard/{id}")
    public MagicCardDto getMagicCard(@PathVariable("id") String id) {
        System.out.println("localhost:8080/magiccard/" + id + " called");
        return magicCardService.findById(id);
    }

    @GetMapping("/magiccards/search")
    @ResponseBody
    public List<MagicCardDto> searchMagicCard(@RequestParam(required = false) String name) {
        System.out.println("localhost:8080/magiccards/search?name=" + name + " called");
//        System.out.println("I tried this: \"" + name + "\"");
        if (name == null) {
            return new ArrayList<>();
        }
        return magicCardService.searchByName(name);
    }
}
