package liza.stage.springtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.springtest.jsonimport.model.MagicCardJson;
import liza.stage.springtest.services.MagicCardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

//@SpringBootApplication
public class DBFiller {

    public static void main(String[] args) {
        SpringApplication.run(DBFiller.class, args);
    }

    @Bean
    CommandLineRunner init(MagicCardService magicCardService) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MagicCardJson> magicCardList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCardJson>>() {
        });
        return args -> {
            System.out.println("Saving..");
            magicCardList.forEach(magicCardService::save);
            System.out.println("Saved.");
            System.out.println(magicCardService.findAll().size());
//            magicCardService.findAll().forEach(System.out::println);
        };
    }

}
