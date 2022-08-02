package works.buddy.library.api.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import works.buddy.library.api.LibraryAPI;
import works.buddy.library.api.RestLibraryAPI;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "works.buddy.library")
public class ApiConfig {

    @Value("${library.api.address}")
    private String address;

    @Autowired
    private RestLibraryAPI restLibraryAPI;

    @Bean
    public JAXRSServerFactoryBean factoryBean() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(LibraryAPI.class);
        factoryBean.setAddress(address);
        factoryBean.setResourceProvider(new SingletonResourceProvider(restLibraryAPI));
        factoryBean.setProviders(List.of(new JacksonJsonProvider(), new NotFoundExceptionMapper(), new BadRequestExceptionMapper()));
        return factoryBean;
    }

}
