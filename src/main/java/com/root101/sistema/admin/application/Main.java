package com.root101.sistema.admin.application;

import com.jhw.module.util.company.ui.module.CompanySwingModule;
import com.jhw.swing.bundles.loading.LoadingProcess;
import com.jhw.swing.bundles.loading.LoadingWorker;
import com.jhw.module.util.bug.module.BugSwingModule;
import com.jhw.module.util.calc.CalcSwingModule;
import com.jhw.swing.material.components.container.panel._PanelGradient;
import javax.swing.JOptionPane;
import com.jhw.module.util.default_config.DefaultConfigSwingModule;
import com.root101.module.control.licence.ui.module.LicenceSwingModule;
import com.jhw.module.util.console.ConsoleSwingModule;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.module.util.authentication_manager.ui.module.AuthSwingModule;
import com.jhw.module.util.local_server.ui.module.LocalServerSwingModule;
import com.jhw.module.util.rest_config.ui.module.RestConfigSwingModule;
import com.jhw.module.util.tec.TecSwingModule;
import com.jhw.swing.material.components.splashScreen.SplashScreen;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.module.admin.seguridad.ui.module.SeguridadSwingModule;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.root101.module.gestion.gastos.ui.module.GastoSwingModule;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    public static final SwingApplication app = new SwingApplication();

    public static void main(String args[]) throws Exception {
        _PanelGradient back = new _PanelGradient();
        back.setIcon(MaterialIcons.CARD_GIFTCARD.deriveIcon(500));

        new LoadingWorker<Void>(SplashScreen.from(back), new LoadingProcess<Void>() {
            @Override
            public Void process() throws Exception {
                app.run();
                app.registerModule(
                        DefaultConfigSwingModule.init(),
                        BugSwingModule.init(),
                        CalcSwingModule.init(),
                        TecSwingModule.init(),
                        LicenceSwingModule.init(),
                        CompanySwingModule.init(),
                        RestConfigSwingModule.init(),
                        AuthSwingModule.init(),
                        LocalServerSwingModule.init(),
                        
                        ContabilidadSwingModule.init(),
                        GastoSwingModule.init(),
                        KanbanSwingModule.init(),
                        SeguridadSwingModule.init()
                );
                return null;
            }

            @Override
            public void completed(Void result) throws Exception {
                System.out.println("Iniciando el sistema....");
                app.registerModule(ConsoleSwingModule.init());
                app.show();
            }

            @Override
            public void errorInProcess(Exception result) {
                System.out.println("Error iniciando el sistema....");
                result.printStackTrace();

                JOptionPane.showConfirmDialog(null,
                        "Error iniciando el sistema. Contacte con soporte.",
                        "Error",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                System.exit(-1);
            }
        });
    }

}