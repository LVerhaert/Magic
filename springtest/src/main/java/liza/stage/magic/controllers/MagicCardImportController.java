package liza.stage.magic.controllers;

import liza.stage.magic.services.MagicCardImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MagicCardImportController {
    private final MagicCardImportService magicCardImportService;

    @GetMapping("/import")
    public String getMagicCards() {
        magicCardImportService.parseJson();
        System.out.println("Json-List: " + magicCardImportService.getJsonList().size());
        System.out.println("List: " + magicCardImportService.getList().size());

        return "hoi";
    }
}
