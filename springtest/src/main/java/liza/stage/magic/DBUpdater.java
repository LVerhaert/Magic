package liza.stage.magic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("liza.stage.magic.domain")
@EnableJpaRepositories("liza.stage.magic.repositories")
public class DBUpdater {

    public static void main(String[] args) {
        SpringApplication.run(DBUpdater.class, args);
    }

}
