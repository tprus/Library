package works.buddy.library.app;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.api.RestLibraryAPI;
import works.buddy.library.app.config.LibraryConfig;

public class WebAppMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class);
        JAXRSServerFactoryBean factoryBean = context.getBean(JAXRSServerFactoryBean.class);
        // I had to extract the line below from ApiConfig in order to get RestLibraryAPI bean
        factoryBean.setResourceProvider(new SingletonResourceProvider(context.getBean(RestLibraryAPI.class)));
        RestLibraryAPI restLibraryAPI = context.getBean(RestLibraryAPI.class);
        Server server = factoryBean.create();
    }
}
