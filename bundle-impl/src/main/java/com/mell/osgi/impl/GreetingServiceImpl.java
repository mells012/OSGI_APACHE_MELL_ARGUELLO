package com.mell.osgi.impl;

import com.mell.osgi.api.GreetingService;
import org.osgi.service.component.annotations.Component;

@Component(service = GreetingService.class)
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hola, " + name + " (desde OSGi)";
    }
}
