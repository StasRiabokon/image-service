package com.nau.config;

import com.nau.service.UserServiceImpl;
import com.nau.utils.DBConnectionManager;
import com.nau.utils.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class AppContextListener implements ServletContextListener {

    private PropertiesLoader props = PropertiesLoader.getInstance();

    public void contextInitialized(ServletContextEvent sce) {

        DBConnectionManager.createConnection(props.getDburl(), props.getDbusername(), props.getDbpassword());

        initDataSource();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionManager.closeConnection();
    }

    private void initDataSource() {
        UserServiceImpl service = UserServiceImpl.getInstance();
//        service.dropTableIfExist("Images");
//        service.dropTableIfExist("Users");
        service.initDatabase();
    }
}
