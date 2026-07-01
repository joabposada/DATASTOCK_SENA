package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialRolUsuarioService {
    private static final Logger logger = Logger.getLogger(HistorialRolUsuarioService.class.getName());

    public HistorialRolUsuarioService() {
        // Sonar
    }

    public void validarAsignacion() {
        logger.log(Level.INFO, "Validando asignación de rol en el sistema...");
    }
}