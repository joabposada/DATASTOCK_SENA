package co.sena.edu.datastock.inventario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioActualController {

    private final InventarioActualService inventarioService;

    public InventarioActualController(InventarioActualService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<List<InventarioActual>> listarTodo() {
        return ResponseEntity.ok(inventarioService.listarTodo());
    }

    @GetMapping("/sitio/{idSitio}")
    public ResponseEntity<List<InventarioActual>> listarPorSitio(@PathVariable Integer idSitio) {
        return ResponseEntity.ok(inventarioService.listarPorSitio(idSitio));
    }
}