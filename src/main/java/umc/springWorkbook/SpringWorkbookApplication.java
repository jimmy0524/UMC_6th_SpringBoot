package umc.springWorkbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringWorkbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkbookApplication.class, args);
	}

}
