package cn.com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
@ImportResource(value = { 
		"classpath:spring.xml"
		})
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
