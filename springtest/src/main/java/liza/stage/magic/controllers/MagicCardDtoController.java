package liza.stage.magic.controllers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardDtoController {
    private final MagicCardService service;

    @GetMapping("/magiccards")
    public Iterable<MagicCardDto> getMagicCards() {
        return service.findAllDto();
    }


    @GetMapping("/magiccard/{id}")
    public MagicCardDto getMagicCard(@PathVariable("id") String id) {
        return service.findDtoById(id);
    }
}
