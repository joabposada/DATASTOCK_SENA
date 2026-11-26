package co.sena.edu.datastock.inventario;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioActualService {

    private final InventarioActualRepository inventarioRepository;

    public InventarioActualService(InventarioActualRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioActual> listarTodo() {
        return inventarioRepository.findAll();
    }

    public List<InventarioActual> listarPorSitio(Integer idSitio) {
        return inventarioRepository.findBySitioIdSitio(idSitio);
    }
}
