package liza.stage.magic.controllers;

import liza.stage.magic.services.MagicCardImportService;
import liza.stage.magic.services.PlayerCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImportController {
    private final MagicCardImportService magicCardImportService;
    private final PlayerCreateService playerCreateService;

    @GetMapping("/importcards")
    public String importMagicCards() {
        System.out.println("localhost:8080/import called");
        magicCardImportService.parseJson();
        String result = "List: " + magicCardImportService.getListSize() + " cards imported";
        System.out.println(result);
        return result;
    }

    @GetMapping("/createplayers")
    public String createPlayers() {
        System.out.println("localhost:8080/createplayers called");
        playerCreateService.delete();
        playerCreateService.create();
        String result = "Sample players created";
        return result;
    }

}
