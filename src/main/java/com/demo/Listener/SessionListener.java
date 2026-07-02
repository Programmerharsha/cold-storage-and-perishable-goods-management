package com.demo.Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        se.getSession().setMaxInactiveInterval(30 * 60); // 30-minute timeout
        System.out.println("Session created. Active sessions: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
        System.out.println("Session destroyed. Active sessions: " + activeSessions);
    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}
