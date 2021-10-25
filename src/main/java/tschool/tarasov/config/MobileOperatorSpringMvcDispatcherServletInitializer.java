package tschool.tarasov.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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

    /**
     * Filter of hidden HTTP fields that registering with app start
     */
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        /**
         *  Security integration to project
         */
//        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
//        root.register(SecurityConfig.class);
//        aServletContext.addListener(new ContextLoaderListener(root));
//
//        aServletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
//                .addMappingForUrlPatterns(null, false, "/*");


        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
