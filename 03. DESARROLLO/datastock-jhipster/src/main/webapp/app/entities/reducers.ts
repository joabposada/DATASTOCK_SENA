import sitio from 'app/entities/sitio/sitio.reducer';
import categoria from 'app/entities/categoria/categoria.reducer';
import marca from 'app/entities/marca/marca.reducer';
import producto from 'app/entities/producto/producto.reducer';
import rol from 'app/entities/rol/rol.reducer';
import usuario from 'app/entities/usuario/usuario.reducer';
import historialRolUsuario from 'app/entities/historial-rol-usuario/historial-rol-usuario.reducer';
import loteEntrada from 'app/entities/lote-entrada/lote-entrada.reducer';
import inventarioActual from 'app/entities/inventario-actual/inventario-actual.reducer';
import historialMovimiento from 'app/entities/historial-movimiento/historial-movimiento.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  sitio,
  categoria,
  marca,
  producto,
  rol,
  usuario,
  historialRolUsuario,
  loteEntrada,
  inventarioActual,
  historialMovimiento,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
