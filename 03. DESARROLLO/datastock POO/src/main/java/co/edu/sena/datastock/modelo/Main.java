package co.edu.sena.datastock.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    // Logger profesional para reemplazar System.out y que el sonar no moleste
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Usamos 'args' para evitar la advertencia
        logger.log(Level.INFO, "Iniciando DATASTOCK con {0} argumentos", args.length);

        Usuario admin = new Usuario();
        admin.setIdUsuario(1);
        admin.setNumeroDocumento("857527228");
        admin.setNombres("Joab");
        admin.setApellido("Rodriguez");
        admin.setCelular("3224150304");
        admin.setCorreo("joab@admin");

        Sitio bodega = new Sitio();
        bodega.setIdSitio(2);
        bodega.setNombreSitio("Bodega Madrid Cundinamarca");
        bodega.setTipoSitio("Secundaria");
        bodega.setDireccion("cra. 2");

        Categoria catPlatos = new Categoria();
        catPlatos.setIdCategoria(10);
        catPlatos.setNombreCategoria("Platos Preparados");

        Marca marcaZenu = new Marca();
        marcaZenu.setIdMarca(3);
        marcaZenu.setNombreMarca("Zenu");

        Producto tamal = new Producto();
        tamal.setIdProducto(10);
        tamal.setNombreProducto("Tamal Zenu");
        tamal.setPresentacion("Bandeja plastica al vacio.");
        tamal.setIdCategoria(10);
        tamal.setIdMarca(3);

        logger.log(Level.INFO, "Usuario cargado: {0} {1} - Rol: Administrador", new Object[]{admin.getNombres(), admin.getApellido()});
        logger.log(Level.INFO, "Sede de operación: {0}", bodega.getNombreSitio());
        logger.log(Level.INFO, "Categoría: {0} | Marca: {1}", new Object[]{catPlatos.getNombreCategoria(), marcaZenu.getNombreMarca()});
        logger.log(Level.INFO, "Producto en stock: {0} ({1})", new Object[]{tamal.getNombreProducto(), tamal.getPresentacion()});
    }
}