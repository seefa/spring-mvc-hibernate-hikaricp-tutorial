package ir.seefa.tutorial.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 14 Sep 2020 T 03:29:11
 */
// 1. read spring-core-tutorial and spring-jdbc-mvc-tutorial codes before starting this project because primary annotations and mvc logic explained there
// 2. to apply configuration over Servlets, we have some options with Spring Framework to make this configurations as Pure Java-based solutions.
// 2.1 - implement WebAppInitializer interface
// 2.2 - extend AbstractDispatcherServletInitializer class
// 2.3 - extend AbstractAnnotationConfigDispatcherServletInitializer
// 2.4 => some initializers act during application starting for example Services or Repository and some works over Web request such as Controllers, Resolvers or Handlers
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 3. Enable Persistence configuration during application loading phase
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                PersistenceConfiguration.class
        };
    }

    // 4. Enable WebMvc configuration during servlet dispatching
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfiguration.class
        };
    }

    // 5. config Servlet mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

}
