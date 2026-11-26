package co.edu.sena.datastock.repository;

import co.edu.sena.datastock.domain.InventarioActual;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InventarioActual entity.
 */
@Repository
public interface InventarioActualRepository extends JpaRepository<InventarioActual, Long> {
    default Optional<InventarioActual> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<InventarioActual> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<InventarioActual> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select inventarioActual from InventarioActual inventarioActual left join fetch inventarioActual.producto left join fetch inventarioActual.sitio",
        countQuery = "select count(inventarioActual) from InventarioActual inventarioActual"
    )
    Page<InventarioActual> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select inventarioActual from InventarioActual inventarioActual left join fetch inventarioActual.producto left join fetch inventarioActual.sitio"
    )
    List<InventarioActual> findAllWithToOneRelationships();

    @Query(
        "select inventarioActual from InventarioActual inventarioActual left join fetch inventarioActual.producto left join fetch inventarioActual.sitio where inventarioActual.id =:id"
    )
    Optional<InventarioActual> findOneWithToOneRelationships(@Param("id") Long id);
}
