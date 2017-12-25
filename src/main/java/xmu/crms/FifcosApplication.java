package xmu.crms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xmu.crms.service.TimerService;

/**
 * @author mads
 */

@SpringBootApplication
@MapperScan("xmu.crms.mapper")
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FifcosApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FifcosApplication.class, args);
	}
}
