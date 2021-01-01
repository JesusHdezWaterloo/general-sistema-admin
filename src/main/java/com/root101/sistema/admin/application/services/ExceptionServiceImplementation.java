package com.root101.sistema.admin.application.services;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.services.ExceptionHandlerServiceFunctional;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ExceptionServiceImplementation extends ExceptionHandlerServiceFunctional {

    public static ExceptionServiceImplementation init() {
        ExceptionServiceImplementation exc = new ExceptionServiceImplementation();
        ExceptionHandler.registerExceptionService(exc);
        return exc;
    }

    private ExceptionServiceImplementation() {
    }

    @Override
    protected void addAll() {
    }
}
