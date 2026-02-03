package sportmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "sportmanagement.entity")
@EnableJpaRepositories(basePackages = "sportmanagement.repo")
public class SportmanagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportmanagementApiApplication.class, args);
    }
}