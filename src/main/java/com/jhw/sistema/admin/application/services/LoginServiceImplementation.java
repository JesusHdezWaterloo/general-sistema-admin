package com.jhw.sistema.admin.application.services;

import com.clean.core.app.services.LoginHandler;
import com.clean.core.app.services.LoginHandlerService;
import com.jhw.sistema.admin.application.Main;
import com.jhw.sistema.admin.application.User;
import com.jhw.utils.security.SHA;
import java.lang.reflect.Method;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LoginServiceImplementation implements LoginHandlerService<String, String, Object, Boolean, User> {

    public static LoginServiceImplementation init() {
        LoginServiceImplementation login = new LoginServiceImplementation();
        LoginHandler.registerLoginHandlerService(login);
        return login;
    }

    private LoginServiceImplementation() {
    }

    @Override
    public Boolean login(String user, String pass, Object... args) {
        //return user.equals("123");

        if (user == null || user.isEmpty() || pass.isEmpty()) {
            return false;
        } else {
            return user.equals("123") && pass.equals(SHA.hash256("123"));
        }
    }

    @Override
    public User user() {
        return Main.user;
    }

    @Override
    public boolean checkAccess(Method method) {
        return true;
    }
}
