package xmu.crms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FifcosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifcosApplication.class, args);
	}
}
