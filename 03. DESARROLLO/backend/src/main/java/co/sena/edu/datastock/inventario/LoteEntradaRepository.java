package co.sena.edu.datastock.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteEntradaRepository extends JpaRepository<LoteEntrada, Integer> {
}
