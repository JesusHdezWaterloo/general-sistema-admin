package com.jhw.sistema.admin.application.services;

import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationServiceFunctional;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class NotificationServiceImplementation extends NotificationServiceFunctional {

    public static NotificationServiceImplementation init() {
        NotificationServiceImplementation notif = new NotificationServiceImplementation();
        NotificationHandler.registerNotificationService(notif);
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
