Laboratorio OSGi con Apache Karaf
Descripción

Este proyecto implementa dos módulos OSGi (bundles) en Apache Karaf utilizando Java 17.
El objetivo es demostrar la modularidad, el bajo acoplamiento y el ciclo de vida dinámico de los servicios dentro del entorno OSGi.

bundle-api: define la interfaz GreetingService.

bundle-impl: implementa la interfaz y la registra en el Service Registry de OSGi.

Requisitos

Java JDK 17 (Eclipse Temurin recomendado)

Apache Karaf 4.4.8

Maven 3.8+

IntelliJ IDEA

Verifica Java:

java -version

Pasos de instalación
1️) Construir los bundles

Desde la raíz del proyecto:

cd bundle-api
mvn clean install

cd ../bundle-impl
mvn clean install

2️) Iniciar Apache Karaf
cd C:\apache-karaf-4.4.8\bin
karaf.bat

3️) Instalar servicios declarativos (SCR)
feature:install scr

4️) Instalar los bundles
bundle:install -s mvn:com.mell.osgi/bundle-api/1.0.0
bundle:install -s mvn:com.mell.osgi/bundle-impl/1.0.0

5️) Verificar el estado
bundle:list
service:list | grep Greeting

Código principal
Interfaz (bundle-api)
package com.mell.osgi.api;

public interface GreetingService {
    String greet(String name);
}

Implementación (bundle-impl)
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

Prueba del ciclo de vida
bundle:stop 58   # Detiene el bundle
bundle:start 58  # Lo vuelve a activar


El servicio se detiene y reaparece dinámicamente sin afectar el resto del sistema.
