package com.jhw.testingmodules.gestion.application;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.swing.app.DefaultSwingApplication;
import static com.clean.swing.app.RootView.LOGIN_NAME;
import com.jhw.files.utils.FILE;
import com.jhw.files.utils.PersonalizationFiles;
import com.jhw.testingmodules.gestion.application.services.ExceptionServiceImplementation;
import com.jhw.testingmodules.gestion.application.services.NavigationServiceImplementation;
import com.jhw.testingmodules.gestion.application.services.NotificationServiceImplementation;
import com.jhw.testingmodules.gestion.application.services.ResourceServiceImplementation;
import com.jhw.testingmodules.gestion.application.services.LoginServiceImplementation;
import com.jhw.licence.services.LicenceHandler;
import com.jhw.personalization.services.PersonalizationHandler;
import com.jhw.swing.models.utils.PersonalizationModel;
import com.jhw.swing.ui.MaterialLookAndFeel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class SwingApplication extends DefaultSwingApplication {

    @Override
    public void init() throws Exception {
        System.out.println("Configurando sistema");

        initRootView(new RootViewFrame(this));

        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        ExceptionServiceImplementation.init();
        NotificationServiceImplementation.init();
        NavigationServiceImplementation.init();
        ResourceServiceImplementation.init();
        LoginServiceImplementation.init();

        //creada la carpeta al iniciar el sistema para que al final cuando se cierre no de error xq no existe
        new File(PersonalizationHandler.getString(PersonalizationFiles.KEY_TEMP_FOLDER)).mkdirs();
    }

    @Override
    public void navigateTo(String string, Object... o) {
        if (LicenceHandler.isLicenceCorrect()) {
            super.navigateTo(string, o);
        } else {
            super.navigateTo(LOGIN_NAME);
        }
    }

    @Override
    public void closeApplication() {
        try {
            boolean deleted = FILE.delete(PersonalizationHandler.getString(PersonalizationFiles.KEY_TEMP_FOLDER));
            if (deleted) {
                return;
            }
        } catch (Exception ex) {
        }
        Notification.showConfirmDialog(NotificationsGeneralType.CONFIRM_ERROR, "Error eliminando la carpeta temporal. Esto NO afecta el sistema,\npero con el tiempo puede que ocupe mucho espacio innecesariamente.");
    }

}
