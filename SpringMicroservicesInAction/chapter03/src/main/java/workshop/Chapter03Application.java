package workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class Chapter03Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter03Application.class, args);
	}
}
