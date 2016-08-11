package de.goldmann.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "de.goldmann.apps.root.dao" })
@EntityScan(basePackages = { "de.goldmann.apps.root.model" })
@ComponentScan(basePackages = { "de.goldmann.apps.root", "de.goldmann.map" })
public class UiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        application.registerShutdownHook(true);
        application.web(true);
        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(UiApplication.class);
        rootContext.registerShutdownHook();

        return application.sources(UiApplication.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
