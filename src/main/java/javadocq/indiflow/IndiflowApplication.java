package javadocq.indiflow;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndiflowApplication {

	public static void main(String[] args) {
		String dbUrl = System.getenv("DB_URL");
		String dbUser = System.getenv("DB_USERNAME");
		String dbPassword = System.getenv("DB_PASSWORD");

		SpringApplication.run(IndiflowApplication.class, args);
	}

}
