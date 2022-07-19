package works.buddy.library.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.app.config.LibraryConfig;
import works.buddy.library.services.ConsoleBookManager;


public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class);
        ConsoleBookManager manager = context.getBean(ConsoleBookManager.class);
        manager.run();
    }
}
