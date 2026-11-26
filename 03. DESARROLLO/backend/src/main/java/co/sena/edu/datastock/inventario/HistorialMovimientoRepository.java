package co.sena.edu.datastock.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialMovimientoRepository extends JpaRepository<HistorialMovimiento, Integer> {
}