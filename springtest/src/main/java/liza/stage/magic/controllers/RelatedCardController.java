package liza.stage.magic.controllers;

import liza.stage.magic.models.entities.MagicCard;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RelatedCardController {
    private final MagicCardService service;

    @GetMapping("magiccardentity/{id}/related")
    public List<MagicCard> getRelatedCards(@PathVariable("id") String id) {
        return service.findAllRelatedTo(id);
    }
}
