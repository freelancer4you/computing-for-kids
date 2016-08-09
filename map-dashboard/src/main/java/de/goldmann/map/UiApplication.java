package de.goldmann.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.goldmann.apps.root,de.goldmann.map")
public class UiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UiApplication.class, args);
    }
}
