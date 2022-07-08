package ex.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/application.properties")
public class AppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsApplication.class, args);
	}

}
