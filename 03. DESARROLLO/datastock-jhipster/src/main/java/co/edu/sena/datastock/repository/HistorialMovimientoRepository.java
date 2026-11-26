package co.edu.sena.datastock.repository;

import co.edu.sena.datastock.domain.HistorialMovimiento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HistorialMovimiento entity.
 */
@Repository
public interface HistorialMovimientoRepository extends JpaRepository<HistorialMovimiento, Long> {
    default Optional<HistorialMovimiento> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<HistorialMovimiento> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<HistorialMovimiento> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select historialMovimiento from HistorialMovimiento historialMovimiento left join fetch historialMovimiento.producto left join fetch historialMovimiento.sitio left join fetch historialMovimiento.usuario",
        countQuery = "select count(historialMovimiento) from HistorialMovimiento historialMovimiento"
    )
    Page<HistorialMovimiento> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select historialMovimiento from HistorialMovimiento historialMovimiento left join fetch historialMovimiento.producto left join fetch historialMovimiento.sitio left join fetch historialMovimiento.usuario"
    )
    List<HistorialMovimiento> findAllWithToOneRelationships();

    @Query(
        "select historialMovimiento from HistorialMovimiento historialMovimiento left join fetch historialMovimiento.producto left join fetch historialMovimiento.sitio left join fetch historialMovimiento.usuario where historialMovimiento.id =:id"
    )
    Optional<HistorialMovimiento> findOneWithToOneRelationships(@Param("id") Long id);
}
