package de.goldmann.map;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.model.Course;

@EnableEncryptableProperties
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "de.goldmann.apps.root.dao" })
@EntityScan(basePackages = { "de.goldmann.apps.root.model" })
@ComponentScan(basePackages = { "de.goldmann.apps.root", "de.goldmann.map" })
public class UiApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private ApplicationContext	rootContext;

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		application.registerShutdownHook(true);
		application.web(true);
		((AnnotationConfigWebApplicationContext) rootContext).register(UiApplication.class);
		((ConfigurableApplicationContext) rootContext).registerShutdownHook();

		return application.sources(UiApplication.class);
	}

	public static void main(final String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	@Override
	public void run(final String... arg0) throws Exception {
		insertDescription("LegoOct2016", "lego-kurs-beschreibung.dat");
		insertDescription("JSOct2016", "javascript-kurs-beschreibung.dat");
		insertDescription("VexOct2016", "vex-kurs-beschreibung.dat");
	}

	private void insertDescription(final String courseId, final String courseDescFile) throws IOException {
		final Course course = courseRepo.findOne(courseId);

		if (course != null && StringUtils.isEmpty(course.getDescription())
		        || course != null && "TODO".equals(course.getDescription())) {
			final InputStream in = new ClassPathResource(courseDescFile).getInputStream();
			course.setDescription(new String(IOUtils.toString(in, Charset.defaultCharset())));
			courseRepo.save(course);
		}
	}
}
