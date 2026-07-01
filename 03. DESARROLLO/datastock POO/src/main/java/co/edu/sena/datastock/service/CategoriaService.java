package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaService {
    private static final Logger logger = Logger.getLogger(CategoriaService.class.getName());

    public CategoriaService() {
        // sonar
    }

    public void validarCategoria() {
        logger.log(Level.INFO, "Validando categorías del catálogo...");
    }
}