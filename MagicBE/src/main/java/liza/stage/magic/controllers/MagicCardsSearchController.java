package liza.stage.magic.controllers;

import liza.stage.magic.models.magiccards.dtos.MagicCardDto;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardsSearchController {
    private final MagicCardService magicCardService;

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
