package tschool.tarasov.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class used instead web.xml. AbstractAnnotationConfigDispatcherServletInitializer is subclass of
 * WebApplicationInitializer, that need to represent DispatcherServlet
 *
 *  getServletMappings() is used instead <servletmapping></servletmapping> to send all requests to Dispatcher
 */

public class MobileOperatorSpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
