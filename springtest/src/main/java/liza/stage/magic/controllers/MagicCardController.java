package liza.stage.magic.controllers;

import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardController {
    private final MagicCardService service;

    @GetMapping("/magiccardsentity")
    public List<MagicCard> getMagicCards() {
        return service.findAll();
    }

    @PostMapping("/magiccardsentity")
    void addMagicCard(@RequestBody MagicCard magicCard) {
        service.save(magicCard);
    }

    @GetMapping("/magiccardentity/{id}")
    public MagicCard getMagicCard(@PathVariable("id") String id) {
        return service.findById(id);
    }

}
