package com.jhw.sistema.admin.application.services;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationServiceFunctional;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class NotificationServiceImplementation extends NotificationServiceFunctional {

    public static NotificationServiceImplementation init() {
        NotificationServiceImplementation notif = new NotificationServiceImplementation();
        Notification.registerNotificationService(notif);
        return notif;
    }

    private NotificationServiceImplementation() {
        addAll();
    }

    @Override
    protected void addNotifications() {
    }

    @Override
    protected void addConfirmDialog() {
    }

    @Override
    protected void addInputDialog() {
    }
}
