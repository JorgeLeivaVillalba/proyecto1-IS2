package com.example.proyecto1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BancoConsumers {

    @JmsListener(destination = "Leiva-ITAU-IN")
    public void processITAU(String message) {
        System.out.println("ITAU procesó: " + message);
        // Simulación de procesamiento adicional aquí
    }

    @JmsListener(destination = "Leiva-ATLAS-IN")
    public void processATLAS(String message) {
        System.out.println("ATLAS procesó: " + message);
        // Simulación de procesamiento adicional aquí
    }

    @JmsListener(destination = "Leiva-FAMILIAR-IN")
    public void processFAMILIAR(String message) {
        System.out.println("FAMILIAR procesó: " + message);
        // Simulación de procesamiento adicional aquí
    }
}