package com.jhw.sistema.admin.application;

import com.root101.clean.swing.app.DefaultRootView;
import com.jhw.sistema.admin.application.dashboard.DashBoardFormateer;
import com.jhw.swing.material.components.dashboard.taskpane.expanded.DashBoardExtends;
import com.jhw.swing.material.components.login.DefaultLoginPanel;
import com.jhw.swing.material.standards.MaterialIcons;
import com.root101.utils.interfaces.Update;
import java.awt.Component;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class RootViewFrame extends DefaultRootView {

    public RootViewFrame(PropertyChangeListener listener) {
        super(listener);
        startLogin();
        startDashboard();

        this.setTitle("Login");
        this.setIconImage(MaterialIcons.SECURITY.getImage());
    }

    private void startLogin() {
        setLogin(new DefaultLoginPanel());
    }

    private void startDashboard() {
        DashBoardExtends dash = new DashBoardExtends() {
            @Override
            public void showView(String view) {//proxy para que actualize
                super.showView(view);
                Component comp = super.getView(view);
                if (comp instanceof Update) {
                    ((Update) comp).update();
                }
            }
        };
        dash.setDashBoardFormatter(DashBoardFormateer.dashBoardFormatter);
        dash.setUpPanelFormatter(DashBoardFormateer.upPanelFormatter);
        dash.setDownPanelFormatter(DashBoardFormateer.downPanelFormatter);
        dash.setMenuFormateer(DashBoardFormateer.collapseMenuFormatter);
        dash.setButtonFormatter(DashBoardFormateer.buttonFormatter);

        setDashBoard(dash);
    }

}
