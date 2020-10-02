package com.jhw.sistema.admin.application;

import com.jhw.module.util.company.ui.module.CompanySwingModule;
import com.jhw.modules.tec.TecSwingModule;
import com.jhw.swing.bundles.loading.LoadingProcess;
import com.jhw.swing.bundles.loading.LoadingWorker;
import com.jhw.module.util.bug.module.BugSwingModule;
import com.jhw.module.util.calc.CalcSwingModule;
import com.jhw.swing.material.components.container.panel._PanelGradient;
import javax.swing.JOptionPane;
import com.jhw.module.util.default_config.DefaultConfigSwingModule;
import com.jhw.module.util.licence.ui.module.LicenceSwingModule;
import com.jhw.module.util.mysql.ui.module.MySQLSwingModule;
import com.jhw.module.util.console.ConsoleSwingModule;
import com.jhw.personalization.ui.module.PersonalizationSwingModule;
import com.jhw.swing.material.components.splashScreen.SplashScreen;
import com.jhw.swing.material.standards.MaterialIcons;
import javax.swing.JPanel;
import com.jhw.module.gestion.gastos.ui.module.GastoSwingModule;
import com.jhw.module.gestion.nomina.ui.module.NominaSwingModule;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    public static final SwingApplication app = new SwingApplication();
    public static User user;

    public static void main(String args[]) throws Exception {
        new LoadingWorker<Void>(new SplashScreen() {
            @Override
            public JPanel mainSplash() {
                _PanelGradient back = new _PanelGradient();
                back.setIcon(MaterialIcons.CARD_GIFTCARD.deriveIcon(500));
                return back;
            }
        }, new LoadingProcess<Void>() {
            @Override
            public Void process() throws Exception {
                app.run();
                app.registerModule(
                        PersonalizationSwingModule.init(),
                        DefaultConfigSwingModule.init(),
                        
                        MySQLSwingModule.init(),
                        
                        BugSwingModule.init(),
                        CalcSwingModule.init(),
                        TecSwingModule.init(),
                        LicenceSwingModule.init(),
                        CompanySwingModule.init(),
                        
                        
                        ContabilidadSwingModule.init(),
                        GastoSwingModule.init(),
                        NominaSwingModule.init(),
                                                
                        KanbanSwingModule.init()
                        
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
