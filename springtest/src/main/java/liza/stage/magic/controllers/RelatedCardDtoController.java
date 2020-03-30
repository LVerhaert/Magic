package liza.stage.magic.controllers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.enums.Relationship;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RelatedCardDtoController {
    private final MagicCardService magicCardService;

    @GetMapping("magiccard/{id}/related")
    public Map<MagicCardDto, Relationship> getRelatedCards(@PathVariable("id") String id) {
        return magicCardService.findAllDtoRelatedTo(id);
    }
}
