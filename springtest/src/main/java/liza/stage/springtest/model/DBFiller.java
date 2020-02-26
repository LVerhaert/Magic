package liza.stage.springtest.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DBFiller implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DBFiller.class, args);
    }

    @Override
    public void run(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<MagicCard> magicCardList = objectMapper.readValue(new File("scryfall-oracle-cards.json"), new TypeReference<List<MagicCard>>() {
        });

        System.out.println(magicCardList.get(0));
        System.out.println(magicCardList.size());
    }
}
