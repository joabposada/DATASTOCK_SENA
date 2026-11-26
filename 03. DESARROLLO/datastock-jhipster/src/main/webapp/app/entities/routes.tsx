import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Sitio from './sitio';
import Categoria from './categoria';
import Marca from './marca';
import Producto from './producto';
import Rol from './rol';
import Usuario from './usuario';
import HistorialRolUsuario from './historial-rol-usuario';
import LoteEntrada from './lote-entrada';
import InventarioActual from './inventario-actual';
import HistorialMovimiento from './historial-movimiento';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="sitio/*" element={<Sitio />} />
        <Route path="categoria/*" element={<Categoria />} />
        <Route path="marca/*" element={<Marca />} />
        <Route path="producto/*" element={<Producto />} />
        <Route path="rol/*" element={<Rol />} />
        <Route path="usuario/*" element={<Usuario />} />
        <Route path="historial-rol-usuario/*" element={<HistorialRolUsuario />} />
        <Route path="lote-entrada/*" element={<LoteEntrada />} />
        <Route path="inventario-actual/*" element={<InventarioActual />} />
        <Route path="historial-movimiento/*" element={<HistorialMovimiento />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
