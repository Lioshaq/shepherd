package md.mi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "file:config/instance.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:config/instance.properties", ignoreResourceNotFound = true)
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
