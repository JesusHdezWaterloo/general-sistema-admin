/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.sistema.admin.application;

import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.clean.swing.app.DefaultSwingApplication;
import com.root101.utils.file.FILE;
import com.root101.utils.file.PersonalizationFiles;
import com.root101.sistema.admin.application.services.ExceptionServiceImplementation;
import com.root101.sistema.admin.application.services.NavigationServiceImplementation;
import com.root101.sistema.admin.application.services.NotificationServiceImplementation;
import com.root101.sistema.admin.application.services.ResourceServiceImplementation;
import com.root101.module.util.personalization.core.domain.Personalization;
import com.root101.module.util.personalization.services.PersonalizationHandler;
import com.root101.swing.bundles.tray.SystemTrayInstaller;
import com.root101.swing.ui.MaterialLookAndFeel;
import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
    public void closeApplication() {
        try {
            boolean deleted = FILE.delete(PersonalizationHandler.getString(PersonalizationFiles.KEY_TEMP_FOLDER));
            if (deleted) {
                return;
            }
        } catch (Exception ex) {
        }
        NotificationHandler.showConfirmDialog(NotificationsGeneralType.CONFIRM_ERROR, "Error eliminando la carpeta temporal. Esto NO afecta el sistema,\npero con el tiempo puede que ocupe mucho espacio innecesariamente.");
    }

}
