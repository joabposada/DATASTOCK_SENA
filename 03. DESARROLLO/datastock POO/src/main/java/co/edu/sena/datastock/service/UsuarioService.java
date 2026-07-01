package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService {
    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    public UsuarioService() {
        // sonar
    }

    public void verificarAcceso() {
        logger.log(Level.INFO, "Verificando credenciales de acceso al sistema...");
    }
}