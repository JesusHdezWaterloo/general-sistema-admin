package com.jhw.sistema.admin.application;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.swing.app.DefaultSwingApplication;
import static com.clean.swing.app.RootView.LOGIN_NAME;
import com.jhw.utils.file.FILE;
import com.jhw.utils.file.PersonalizationFiles;
import com.jhw.sistema.admin.application.services.ExceptionServiceImplementation;
import com.jhw.sistema.admin.application.services.NavigationServiceImplementation;
import com.jhw.sistema.admin.application.services.NotificationServiceImplementation;
import com.jhw.sistema.admin.application.services.ResourceServiceImplementation;
import com.jhw.module.util.licence.services.LicenceHandler;
import com.jhw.module.util.personalization.core.domain.Personalization;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.bundles.tray.SystemTrayInstaller;
import com.jhw.swing.ui.MaterialLookAndFeel;
import java.io.File;
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

        //creada la carpeta al iniciar el sistema para que al final cuando se cierre no de error xq no existe
        new File(PersonalizationHandler.getString(PersonalizationFiles.KEY_TEMP_FOLDER)).mkdirs();
    }

    @Override
    public void show() {
        super.show();

        //aqui, despues que se instalaron los modulos para que coja el icon
        if (PersonalizationHandler.getBoolean(Personalization.KEY_HIDE_TO_SYSTEM_TRAY)) {
            SystemTrayInstaller.builder(rootView()).build();
        }
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
