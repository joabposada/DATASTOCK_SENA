package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SitioService {
    private static final Logger logger = Logger.getLogger(SitioService.class.getName());

    public SitioService() {
        // sonar
    }

    public void verificarDisponibilidadBodega() {
        logger.log(Level.INFO, "Verificando espacio en bodegas y puntos de venta...");
    }
}