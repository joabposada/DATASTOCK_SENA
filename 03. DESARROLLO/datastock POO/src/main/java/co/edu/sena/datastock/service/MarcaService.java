package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MarcaService {
    private static final Logger logger = Logger.getLogger(MarcaService.class.getName());

    public MarcaService() {
        // sonar
    }

    public void validarMarca() {
        logger.log(Level.INFO, "Procesando marcas de productos empacados...");
    }
}