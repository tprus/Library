package works.buddy.library.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;
@Configuration
@ComponentScan(basePackages = "works.buddy.library")
public class LibraryConfig {
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}
