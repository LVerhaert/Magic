package liza.stage.magic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("liza.stage.magic.models")
@EnableJpaRepositories("liza.stage.magic.repositories")
@ComponentScan("liza.stage.magic.*")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
