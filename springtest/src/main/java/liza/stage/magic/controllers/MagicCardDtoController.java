package liza.stage.magic.controllers;

import liza.stage.magic.models.dtos.MagicCardDto;
import liza.stage.magic.models.dtos.PagingResult;
import liza.stage.magic.services.MagicCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MagicCardDtoController {
    private final MagicCardService service;

    @GetMapping("/magiccards")
    @ResponseBody
    public PagingResult<MagicCardDto> getMagicCardsOnePage(@RequestParam(required = false) String pageIndex,
                                                           @RequestParam(required = false) String pageSize) {
        System.out.println("I tried this: pageIndex=" + pageIndex + ", pageSize=" + pageSize);
        if (pageIndex == null) {
            pageIndex = "0";
        }
        if (pageSize == null || "0".equals(pageSize)) {
            pageSize = "20";
        }
        System.out.println("Now trying this: pageIndex=" + pageIndex + ", pageSize=" + pageSize);
        return service.findOnePageDto(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
    }

    @GetMapping("/magiccard/{id}")
    public MagicCardDto getMagicCard(@PathVariable("id") String id) {
        return service.findDtoById(id);
    }
}
