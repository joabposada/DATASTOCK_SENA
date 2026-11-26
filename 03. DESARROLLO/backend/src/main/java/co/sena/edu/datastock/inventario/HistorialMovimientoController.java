package co.sena.edu.datastock.inventario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class HistorialMovimientoController {

    private final HistorialMovimientoService movimientoService;

    public HistorialMovimientoController(HistorialMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialMovimiento>> listar() {
        return ResponseEntity.ok(movimientoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<HistorialMovimiento> registrar(@RequestBody MovimientoRequestDTO dto) {
        return new ResponseEntity<>(movimientoService.registrarMovimiento(dto), HttpStatus.CREATED);
    }
}
