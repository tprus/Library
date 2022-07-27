package works.buddy.library.app.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.config.LibraryConfig;
import works.buddy.library.app.console.services.ConsoleBookManager;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class);
        context.getBean(ConsoleBookManager.class).run();
    }
}
