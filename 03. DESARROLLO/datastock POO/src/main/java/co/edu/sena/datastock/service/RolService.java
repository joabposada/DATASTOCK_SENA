package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RolService {
    private static final Logger logger = Logger.getLogger(RolService.class.getName());

    public RolService() {
        // sonar
    }

    public void validarPermisos() {
        logger.log(Level.INFO, "Calculando niveles de permiso según el rol asignado...");
    }
}