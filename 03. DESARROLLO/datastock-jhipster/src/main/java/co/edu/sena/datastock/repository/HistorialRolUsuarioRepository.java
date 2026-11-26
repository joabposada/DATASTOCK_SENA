package co.edu.sena.datastock.repository;

import co.edu.sena.datastock.domain.HistorialRolUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HistorialRolUsuario entity.
 */
@Repository
public interface HistorialRolUsuarioRepository extends JpaRepository<HistorialRolUsuario, Long> {
    default Optional<HistorialRolUsuario> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<HistorialRolUsuario> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<HistorialRolUsuario> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select historialRolUsuario from HistorialRolUsuario historialRolUsuario left join fetch historialRolUsuario.usuario left join fetch historialRolUsuario.rol",
        countQuery = "select count(historialRolUsuario) from HistorialRolUsuario historialRolUsuario"
    )
    Page<HistorialRolUsuario> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select historialRolUsuario from HistorialRolUsuario historialRolUsuario left join fetch historialRolUsuario.usuario left join fetch historialRolUsuario.rol"
    )
    List<HistorialRolUsuario> findAllWithToOneRelationships();

    @Query(
        "select historialRolUsuario from HistorialRolUsuario historialRolUsuario left join fetch historialRolUsuario.usuario left join fetch historialRolUsuario.rol where historialRolUsuario.id =:id"
    )
    Optional<HistorialRolUsuario> findOneWithToOneRelationships(@Param("id") Long id);
}
