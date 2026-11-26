package co.sena.edu.datastock.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventarioActualRepository extends JpaRepository<InventarioActual, InventarioActualId> {
    List<InventarioActual> findBySitioIdSitio(Integer idSitio);
}
