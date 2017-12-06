package xmu.crms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FifcosApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FifcosApplication.class, args);
	}
}
