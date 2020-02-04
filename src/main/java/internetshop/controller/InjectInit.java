package internetshop.controller;

import internetshop.lib.Injector;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InjectInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
