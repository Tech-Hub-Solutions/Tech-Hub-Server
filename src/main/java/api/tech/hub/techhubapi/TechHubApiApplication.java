package api.tech.hub.techhubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TechHubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechHubApiApplication.class, args);
	}

}
