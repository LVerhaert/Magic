package liza.stage.magic.controllers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.dtos.PagingResult;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardsController {
    private final MagicCardService magicCardService;

    @GetMapping("/magiccards")
    @ResponseBody
    public PagingResult<MagicCardDto> getMagicCardsOnePage(@RequestParam(required = false, defaultValue = "0") String pageIndex,
                                                           @RequestParam(required = false, defaultValue = "20") String pageSize) {
        System.out.println("localhost:8080/magiccards?pageIndex=" + pageIndex + ", pageSize=" + pageSize + " called");
//        if (pageIndex == null) {
//            pageIndex = "0";
//        }
//        if (pageSize == null) {
//            pageSize = "20";
//        }
        System.out.println("Now trying this: pageIndex=" + pageIndex + ", pageSize=" + pageSize);
        return magicCardService.findOnePageDto(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
    }

}
