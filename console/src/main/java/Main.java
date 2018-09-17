

import something.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app.cfg.xml");
        App app = context.getBean("app", App.class);
        app.run();
    }
}