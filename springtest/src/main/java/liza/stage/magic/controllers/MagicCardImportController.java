package liza.stage.magic.controllers;

import liza.stage.magic.services.MagicCardImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardImportController {
    private final MagicCardImportService importService;

    @GetMapping("/import")
    public String getMagicCards() {
        importService.parseJson();
        String result = "Json-List: " + importService.getJsonList().size()
                + "\nList: " + importService.getList().size();
        System.out.println(result);
        return result;
    }

    @GetMapping("/importlight")
    public String getMagicCardsLight() {
        importService.parseJsonLight();
        String result = "Json-List: " + importService.getJsonList().size()
                + "\nList: " + importService.getList().size();
        System.out.println(result);
        return result;
    }

}
