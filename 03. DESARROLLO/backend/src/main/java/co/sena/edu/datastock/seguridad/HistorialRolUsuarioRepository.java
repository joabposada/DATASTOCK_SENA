package co.sena.edu.datastock.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRolUsuarioRepository extends JpaRepository<HistorialRolUsuario, HistorialRolUsuarioId> {
}
