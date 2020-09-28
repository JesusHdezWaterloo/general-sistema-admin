package com.jhw.testingmodules.gestion.application.services;

import com.clean.core.app.services.Navigation;
import com.clean.core.app.services.NavigationService;
import static com.jhw.testingmodules.gestion.application.Main.app;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class NavigationServiceImplementation implements NavigationService {

    public static NavigationServiceImplementation init() {
        NavigationServiceImplementation nav = new NavigationServiceImplementation();
        Navigation.registerNavigationService(nav);
        return nav;
    }

    private NavigationServiceImplementation() {
    }

    @Override
    public void navigateTo(String string, Object... o) {
        app.navigateTo(string, o);
    }

}
