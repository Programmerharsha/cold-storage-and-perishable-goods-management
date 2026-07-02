package com.demo.Listener;

import com.demo.DBConnection.DBConnection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Cold Storage Management System Started ===");
        // Pre-initialize the DB connection pool
        DBConnection.getConnection();
        System.out.println("Database connection initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnection.closeConnection();
        System.out.println("=== Cold Storage Management System Stopped ===");
    }
}
