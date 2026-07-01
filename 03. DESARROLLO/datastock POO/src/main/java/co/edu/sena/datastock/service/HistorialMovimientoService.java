package co.edu.sena.datastock.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialMovimientoService {
    private static final Logger logger = Logger.getLogger(HistorialMovimientoService.class.getName());

    public HistorialMovimientoService() {
        //  Sonar
    }

    public void registrarLogMovimiento(String tipo, int cantidad) {
        logger.log(Level.INFO, "Movimiento tipo {0} registrado por cantidad de {1}", new Object[]{tipo, cantidad});
    }
}