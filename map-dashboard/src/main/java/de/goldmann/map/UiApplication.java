package de.goldmann.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.goldmann.apps.root.dao")
@EntityScan("de.goldmann.apps.root.model")
@ComponentScan(basePackages = "de.goldmann.apps.root,de.goldmann.map")
public class UiApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(UiApplication.class, args);
    }
}
