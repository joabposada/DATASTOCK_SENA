package co.sena.edu.datastock.seguridad;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-roles")
public class HistorialRolUsuarioController {

    private final HistorialRolUsuarioService historialService;

    public HistorialRolUsuarioController(HistorialRolUsuarioService historialService) {
        this.historialService = historialService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialRolUsuario>> listar() {
        return ResponseEntity.ok(historialService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<HistorialRolUsuario> asignar(@RequestBody AsignarRolRequestDTO dto) {
        return new ResponseEntity<>(historialService.asignarRol(dto), HttpStatus.CREATED);
    }
}
