package liza.stage.springtest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.springtest.jsonimport.model.MagicCardJson;
import liza.stage.springtest.repositories.MagicCardRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class MagicCardImportService {
    private MagicCardRepository magicCardRepository;

    public List<MagicCardJson> parseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MagicCardJson> magicCardList = null;
        try {
            magicCardList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
//        saveAll(magicCardList);
        return magicCardList;
    }

    public List<MagicCardJson> parseJsonLight() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MagicCardJson> magicCardList = null;
        try {
            magicCardList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
//        saveAll(magicCardList.subList(0,99));
        return magicCardList;
    }

//    public void saveAll(List<MagicCardJson> magicCardList) {
//        magicCardRepository.saveAll(magicCardList);
//    }

//    public void save(MagicCardJson magicCard) {
//        magicCardRepository.save(magicCard);
//    }

}
