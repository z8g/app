/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.nwsuaf.controller.util;

import club.nwsuaf.model.Notification;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class NotificationUtil {

    public static void add(HttpSession session, Notification notification) {
        run(session, notification);
    }

    public static void add(HttpSession session, String content, String type) {
        Notification notification = new Notification(content, type);
        run(session, notification);
    }

    public static void add(HttpSession session, String content) {
        Notification notification = new Notification(content);
        run(session, notification);
    }

    private static void run(HttpSession session, Notification notification) {
        List<Notification> notificationList = (List<Notification>) session.getAttribute("notificationList");
        if (notificationList != null) {
            notificationList.add(notification);
            session.setAttribute("notificationList", notificationList);
            session.setAttribute("lastNotification", notification);
        } else {
            List<Notification> newList = new LinkedList<>();
            newList.add(notification);
            session.setAttribute("notificationList", newList);
        }
    }
    
    public static void deleteAll(HttpSession session) {
        session.removeAttribute("notificationList");
    }

}
