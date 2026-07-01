package co.edu.sena.datastock.service;

import co.edu.sena.datastock.modelo.Producto;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoService {

    private static final Logger logger = Logger.getLogger(ProductoService.class.getName());

    public ProductoService() {
        // para que no moleste el sonar
    }

    public void registrarNuevoProducto(Producto producto) {

        if (producto == null || producto.getNombreProducto() == null) {
            logger.log(Level.WARNING, "Error: No se puede registrar un producto vacío o sin nombre.");
            return;
        }

        logger.log(Level.INFO, "Validación exitosa. Guardando el producto en la base de datos: {0}", producto.getNombreProducto());
    }

    public void actualizarStock(Producto producto, int cantidad) {
        logger.log(Level.INFO, "Actualizando {0} unidades para el producto {1}", new Object[]{cantidad, producto.getNombreProducto()});
        // Lógica para sumar o restar inventario
    }
}