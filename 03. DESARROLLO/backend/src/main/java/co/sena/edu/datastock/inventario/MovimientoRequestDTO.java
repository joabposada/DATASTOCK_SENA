package co.sena.edu.datastock.inventario;

public record MovimientoRequestDTO(
        Integer idProducto,
        Integer idSitio,
        Integer idSitioDestino,
        Integer idUsuario,
        String tipoMovimiento,
        Integer cantidad
) {}
