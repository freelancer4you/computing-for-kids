package de.goldmann.map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class SpringProfileActivator implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /** Spring-Profil, welches im Development-Mode zu aktivieren ist. */
    public static final String DEVELOPMENT_MODE_SPRING_PROFIL = "development";

    /** Privater Logger der Klasse. */
    // private final Logger logger =
    // LoggerFactory.getLogger(SpringProfileActivator.class);

    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();


    }

}
