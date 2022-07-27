package works.buddy.library.app.web;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.app.config.LibraryConfig;

public class AppServer {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class);
        context.getBean(JAXRSServerFactoryBean.class).create();
    }
}
