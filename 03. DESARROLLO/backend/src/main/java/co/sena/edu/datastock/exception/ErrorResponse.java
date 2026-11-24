// Creado por lo erorres en GlobalExcelptionHandler (evitar usar map) (IA)
package co.sena.edu.datastock.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje
) {}
