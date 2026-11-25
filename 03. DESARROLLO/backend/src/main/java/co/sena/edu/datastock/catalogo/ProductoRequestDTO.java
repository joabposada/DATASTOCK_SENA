package co.sena.edu.datastock.catalogo;

public record ProductoRequestDTO(
        String nombreProducto,
        String presentacion,
        Integer idCategoria,
        Integer idMarca
) {}