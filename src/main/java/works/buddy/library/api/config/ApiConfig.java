package works.buddy.library.api.config;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import works.buddy.library.api.LibraryAPI;

@Configuration
@ComponentScan(basePackages = "works.buddy.library")
public class ApiConfig {

    @Bean
    public static JAXRSServerFactoryBean factoryBean() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(LibraryAPI.class);
//        factoryBean.setResourceProvider(new SingletonResourceProvider(new RestLibraryAPI()));
        factoryBean.setAddress("http://localhost:8080/");
        return factoryBean;
    }

}
