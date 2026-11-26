package co.sena.edu.datastock.inventario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes-entrada")
public class LoteEntradaController {

    private final LoteEntradaService loteService;

    public LoteEntradaController(LoteEntradaService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<LoteEntrada>> listar() {
        return ResponseEntity.ok(loteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteEntrada> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(loteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LoteEntrada> registrar(@RequestBody LoteEntradaRequestDTO dto) {
        return new ResponseEntity<>(loteService.registrarLote(dto), HttpStatus.CREATED);
    }
}
