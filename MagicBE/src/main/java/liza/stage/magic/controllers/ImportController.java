package liza.stage.magic.controllers;

import liza.stage.magic.services.MagicCardImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImportController {
    private final MagicCardImportService magicCardImportService;

    @Profile(value = "final")
    @GetMapping("/import")
    public String getMagicCards() {
        System.out.println("localhost:8080/import called");
        magicCardImportService.parseJson();
        String result = "Json-List: " + magicCardImportService.getJsonList().size()
                + "\nList: " + magicCardImportService.getList().size();
        System.out.println(result);
        return result;
    }

    @Profile(value = "development")
    @GetMapping("/importlight")
    public String getMagicCardsLight() {
        System.out.println("localhost:8080/importlight called");
        magicCardImportService.parseJsonLight();
        String result = "Json-List: " + magicCardImportService.getJsonList().size()
                + "\nList: " + magicCardImportService.getList().size();
        System.out.println(result);
        return result;
    }

}
