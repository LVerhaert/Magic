package liza.stage.springtest.controllers;

import liza.stage.springtest.model.MagicCard;
import liza.stage.springtest.repositories.MagicCardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardController {
    private final MagicCardRepository magicCardRepository;

    public MagicCardController(MagicCardRepository magicCardRepository) {
        this.magicCardRepository = magicCardRepository;
    }

    @GetMapping("/magiccards")
    public List<MagicCard> getMagicCards() {
        return (List<MagicCard>) magicCardRepository.findAll();
    }

    @PostMapping("/magiccards")
    void addMagicCard(@RequestBody MagicCard magicCard) {
        magicCardRepository.save(magicCard);
    }

}
