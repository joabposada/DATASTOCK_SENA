package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarioActualService {
    private static final Logger logger = Logger.getLogger(InventarioActualService.class.getName());

    public InventarioActualService() {
        //  Sonar
    }

    public void consultarStockDisponible(int idProducto) {
        logger.log(Level.INFO, "Consultando stock actual para el producto ID: {0}", idProducto);
    }
}