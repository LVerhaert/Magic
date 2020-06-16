package liza.stage.magic.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.magic.mappers.importmappers.MagicCardImportMapper;
import liza.stage.magic.models.magiccards.magiccardentities.MagicCardEntity;
import liza.stage.magic.models.magiccards.magiccardjson.MagicCardJson;
import liza.stage.magic.repositories.MagicCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class MagicCardImportService {
    @Autowired
    private MagicCardRepository magicCardRepository;
    @Autowired
    private MagicCardImportMapper magicCardImportMapper;

    public void parseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<MagicCardJson> magicCardJsonList = objectMapper.readValue(new File("src/main/resources/scryfall-oracle-cards.json"),
                    new TypeReference<List<MagicCardJson>>() {
                    });
            saveAll(magicCardJsonList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAll(List<MagicCardJson> magicCardJsonList) {
        int size = 0;
        for (MagicCardJson magicCardJson : magicCardJsonList) {
            size++;
            if (size % 100 == 0) {
                System.out.println("Size is: " + size);
            }
            save(magicCardJson);
        }
    }

    private void save(MagicCardJson magicCardJson) {
        MagicCardEntity magicCardEntity = magicCardImportMapper.map(magicCardJson);
        magicCardRepository.save(magicCardEntity);
    }


    public long getListSize() {
        return magicCardRepository.count();
    }

}
