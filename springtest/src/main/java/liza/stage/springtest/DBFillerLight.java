package liza.stage.springtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liza.stage.springtest.model.MagicCard;
import liza.stage.springtest.service.MagicCardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DBFillerLight {

    public static void main(String[] args) {
        SpringApplication.run(DBFillerLight.class, args);
    }

    @Bean
    CommandLineRunner init(MagicCardService magicCardService) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MagicCard> magicCardList = objectMapper.readValue(new File("D:\\lizav\\Documents\\Educatie\\Stage\\magic\\springtest\\src\\main\\resources\\scryfall-oracle-cards.json"), new TypeReference<List<MagicCard>>() {
        });
        return args -> {
            System.out.println("Saving..");
            magicCardList.subList(0,99).forEach(magicCardService::save);
            System.out.println("Saved.");
            System.out.println(magicCardService.findAll().size());
//            magicCardService.findAll().forEach(System.out::println);
        };
    }

}
