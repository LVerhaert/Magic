package liza.stage.springtest.controllers;

import liza.stage.springtest.domain.MagicCard;
import liza.stage.springtest.services.MagicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardController {
    @Autowired
    private final MagicCardService magicCardService;
//    @Autowired
//    private final MagicCardRepository magicCardRepository;

    public MagicCardController(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @GetMapping("/magiccards")
    public List<MagicCard> getMagicCards() {
        return magicCardService.findAll();
    }

    @PostMapping("/magiccards")
    void addMagicCard(@RequestBody MagicCard magicCard) {
        magicCardService.save(magicCard);
    }

}
