// interfaz hecha junto con la API que Expone los endpoints REST
package co.sena.edu.datastock.infraestructura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sitios")
public class SitioController {

    private final SitioService sitioService;

    public SitioController(SitioService sitioService) {
        this.sitioService = sitioService;
    }

    @GetMapping
    public ResponseEntity<List<Sitio>> listar() {
        return ResponseEntity.ok(sitioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sitio> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(sitioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Sitio> crear(@RequestBody Sitio sitio) {
        Sitio creado = sitioService.guardar(sitio);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sitio> actualizar(@PathVariable Integer id, @RequestBody Sitio sitio) {
        return ResponseEntity.ok(sitioService.actualizar(id, sitio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        sitioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
