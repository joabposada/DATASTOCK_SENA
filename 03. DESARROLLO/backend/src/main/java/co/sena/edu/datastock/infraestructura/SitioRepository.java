// interfaz hecha junto con la API que nos regala todo el SQL sin consultar
package co.sena.edu.datastock.infraestructura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitioRepository extends JpaRepository<Sitio, Integer> {
}
