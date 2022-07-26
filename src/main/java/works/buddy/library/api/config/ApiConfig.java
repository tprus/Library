package works.buddy.library.api.config;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.annotation.Bean;
import works.buddy.library.api.LibraryAPI;
import works.buddy.library.api.RestLibraryAPI;

public class ApiConfig {
    @Bean
    public static JAXRSServerFactoryBean factoryBean (){
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(LibraryAPI .class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new RestLibraryAPI()));
        factoryBean.setAddress("http://localhost:8080/");
        return factoryBean;
    }

}
