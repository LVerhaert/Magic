package liza.stage.magic.controllers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardSearchController {
    private final MagicCardService service;

    @GetMapping("/magiccards/search")
    @ResponseBody
    public List<MagicCardDto> searchMagicCard(@RequestParam(required=false) String name) {
        System.out.println("I tried this: \"" + name + "\"");
        if (name == null) {
            return new ArrayList<MagicCardDto>();
        }
        return service.searchDtoByName(name);
    }



}
