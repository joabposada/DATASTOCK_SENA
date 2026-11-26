package co.sena.edu.datastock.inventario;

import java.time.LocalDate;

public record LoteEntradaRequestDTO(
        Integer idProducto,
        Integer idSitio,
        Integer idUsuario,
        LocalDate fechaVencimiento,
        Integer cantidadIngresada,
        String facturaProveedor
) {}
