package com.nau.utils;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class PropertiesLoader {

    private String dburl;
    private String dbusername;
    private String dbpassword;
    private String host;
    private String port;
    private String user;
    private String pass;

    public static volatile PropertiesLoader instance;

    public static PropertiesLoader getInstance() {
        PropertiesLoader localInstance = instance;
        if (localInstance == null) {
            synchronized (PropertiesLoader.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PropertiesLoader();
                }
            }
        }
        return localInstance;
    }

    private PropertiesLoader() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("props.properties");

        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        dburl = props.getProperty("dbUrl");
        dbusername = props.getProperty("dbUserName");
        dbpassword = props.getProperty("dbPassword");
        host = props.getProperty("host");
        port = props.getProperty("port");
        user = props.getProperty("user");
        pass = props.getProperty("pass");

    }

}
