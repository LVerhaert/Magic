package liza.stage.magic.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.magic.mappers.importmappers.MagicCardImportMapper;
import liza.stage.magic.models.magiccards.entities.MagicCardEntity;
import liza.stage.magic.models.magiccards.json.MagicCardJson;
import liza.stage.magic.repositories.magiccards.MagicCardEntitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MagicCardImportService {
    private final MagicCardEntitiesRepository magicCardEntitiesRepository;
    private final MagicCardImportMapper magicCardImportMapper;
    private List<MagicCardJson> magicCardJsonList;

    public void parseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        magicCardJsonList = null;
        try {
            magicCardJsonList = objectMapper.readValue(new File("src/main/resources/scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
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
            magicCardJsonList = objectMapper.readValue(new File("src/main/resources/scryfall-oracle-cards-light.json"), new TypeReference<List<MagicCardJson>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAll(magicCardJsonList);
    }

    public void saveAll(List<MagicCardJson> magicCardJsonList) {
        int size = 0;
        for (MagicCardJson magicCardJson : magicCardJsonList) {
            size++;
            if (size % 100 == 0) {
                System.out.println("Size is: " + size);
            }
            save(magicCardJson);
        }
    }

    public void save(MagicCardJson magicCardJson) {
        MagicCardEntity magicCardEntity = magicCardImportMapper.map(magicCardJson);
//        List<MagicCardEntity> mce = magicCardEntitiesRepository.findAll();
//        int size = mce.size();
//        if (size % 100 == 0) {
//            System.out.println("Size is: " + size);
//        }
        magicCardEntitiesRepository.save(magicCardEntity);
    }

    public List<MagicCardEntity> getList() {
        return (List<MagicCardEntity>)magicCardEntitiesRepository.findAll();
    }

    public List<MagicCardJson> getJsonList() {
        return magicCardJsonList;
    }

}
