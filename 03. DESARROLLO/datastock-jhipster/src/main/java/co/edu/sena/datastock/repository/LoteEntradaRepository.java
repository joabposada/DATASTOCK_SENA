package co.edu.sena.datastock.repository;

import co.edu.sena.datastock.domain.LoteEntrada;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LoteEntrada entity.
 */
@Repository
public interface LoteEntradaRepository extends JpaRepository<LoteEntrada, Long> {
    default Optional<LoteEntrada> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<LoteEntrada> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<LoteEntrada> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select loteEntrada from LoteEntrada loteEntrada left join fetch loteEntrada.producto left join fetch loteEntrada.sitio",
        countQuery = "select count(loteEntrada) from LoteEntrada loteEntrada"
    )
    Page<LoteEntrada> findAllWithToOneRelationships(Pageable pageable);

    @Query("select loteEntrada from LoteEntrada loteEntrada left join fetch loteEntrada.producto left join fetch loteEntrada.sitio")
    List<LoteEntrada> findAllWithToOneRelationships();

    @Query(
        "select loteEntrada from LoteEntrada loteEntrada left join fetch loteEntrada.producto left join fetch loteEntrada.sitio where loteEntrada.id =:id"
    )
    Optional<LoteEntrada> findOneWithToOneRelationships(@Param("id") Long id);
}
