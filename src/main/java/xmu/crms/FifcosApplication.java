package xmu.crms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mads
 */

@SpringBootApplication
@MapperScan("xmu.crms.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FifcosApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FifcosApplication.class, args);
	}
}
