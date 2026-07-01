package co.edu.sena.datastock.service;

import co.edu.sena.datastock.modelo.LoteEntrada;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.ZoneId; // Importante agregar este

public class LoteEntradaService {
    private static final Logger logger = Logger.getLogger(LoteEntradaService.class.getName());

    public LoteEntradaService() {
        // Sonar
    }

    public void verificarVencimiento(LoteEntrada lote) {
        LocalDate hoy = LocalDate.now(ZoneId.systemDefault());

        if (lote.getFechaVencimiento().isBefore(hoy.plusMonths(1))) {
            logger.log(Level.WARNING, "ALERTA: El lote {0} vence en menos de un mes", lote.getIdLote());
        }
    }
}