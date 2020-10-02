package com.jhw.sistema.admin.application.services;

import com.clean.core.domain.services.Resource;
import com.clean.core.domain.services.ResourceBundleUtils;
import com.clean.core.domain.services.ResourceService;
import com.clean.core.domain.services.DefaultResourceBundleService;
import java.net.MalformedURLException;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ResourceServiceImplementation implements ResourceService {

    public static final String RESOURCE_URL = "admin";

    private final DefaultResourceBundleService resourceService;

    public static ResourceServiceImplementation init() throws MalformedURLException {
        ResourceServiceImplementation res = new ResourceServiceImplementation();
        Resource.registerResourceService(res);
        return res;
    }

    private ResourceServiceImplementation() throws MalformedURLException {
        resourceService = new DefaultResourceBundleService(
                ResourceBundleUtils.fromExternalFile(RESOURCE_URL,
                        ResourceBundleUtils.SPANISH));
    }

    @Override
    public String getString(String string) {
        return resourceService.getString(string);
    }

    @Override
    public Object getObject(String string) {
        return resourceService.getObject(string);
    }

    @Override
    public boolean contain(String string) {
        return resourceService.contain(string);
    }
}
