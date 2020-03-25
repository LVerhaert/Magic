package liza.stage.magic.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.magic.domain.MagicCard;
import liza.stage.magic.jsonimport.model.MagicCardJson;
import liza.stage.magic.mappers.importmappers.MagicCardMapper;
import liza.stage.magic.repositories.MagicCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MagicCardImportService {
    private final MagicCardRepository magicCardRepository;
    List<MagicCardJson> magicCardJsonList;

    public void parseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        magicCardJsonList = null;
        try {
            magicCardJsonList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAll(magicCardJsonList);
    }

    public void parseJsonLight() {
        ObjectMapper objectMapper = new ObjectMapper();
        magicCardJsonList = null;
        try {
            magicCardJsonList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAll(magicCardJsonList.subList(0, 99));
    }

    public void saveAll(List<MagicCardJson> magicCardJsonList) {
        for (MagicCardJson magicCardJson : magicCardJsonList) {
            save(magicCardJson);
        }
    }

    public void save(MagicCardJson magicCardJson) {
        MagicCard magicCard = MagicCardMapper.INSTANCE.toEntity(magicCardJson);
        magicCardRepository.save(magicCard);
    }

    public List<MagicCard> getList() {
        return magicCardRepository.findAll();
    }

    public List<MagicCardJson> getJsonList() {
        return magicCardJsonList;
    }

}
