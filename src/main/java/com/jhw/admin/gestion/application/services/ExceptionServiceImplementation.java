package com.jhw.admin.gestion.application.services;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.ExceptionHandlerServiceFunctional;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ExceptionServiceImplementation extends ExceptionHandlerServiceFunctional {

    public static ExceptionServiceImplementation init() {
        ExceptionServiceImplementation exc = new ExceptionServiceImplementation();
        ExceptionHandler.registerExceptionHandlerService(exc);
        return exc;
    }

    private ExceptionServiceImplementation() {
    }

    @Override
    protected void addAll() {
    }
}
