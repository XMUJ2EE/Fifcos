package xmu.crms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mads
 */

@SpringBootApplication
@MapperScan("xmu.crms.mapper")
public class FifcosApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FifcosApplication.class, args);
	}
}
