package de.goldmann.map;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class DashboardWebApplicationInitializer implements WebApplicationInitializer {

    private static final String SPRING_WEB_SERVICE_DISPATCHER = "SpringWebServiceDispatcher";

    private static final String CHARACTER_ENCODING_FILTER_KEY = "CharacterEncodingFilter";

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final String DEFAULT_PATH = "/*";

    private static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(UiApplication.class);
        rootContext.setServletContext(servletContext);

        final FilterRegistration charEncodingfilterReg = servletContext.addFilter(CHARACTER_ENCODING_FILTER_KEY,
                new CharacterEncodingFilter(DEFAULT_ENCODING));
        charEncodingfilterReg.addMappingForUrlPatterns(null, false, DEFAULT_PATH);

        final ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
        contextLoaderListener.setContextInitializers(new SpringProfileActivator());
        servletContext.addListener(contextLoaderListener);

        final EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.ERROR, DispatcherType.REQUEST);
        servletContext.addFilter(SPRING_SECURITY_FILTER_CHAIN, new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN))
        .addMappingForUrlPatterns(dispatcherTypes, false, DEFAULT_PATH);

        // Servlets
        servletContext.addServlet(SPRING_WEB_SERVICE_DISPATCHER, new DispatcherServlet(rootContext))
                .addMapping(DEFAULT_PATH);
    }

}
