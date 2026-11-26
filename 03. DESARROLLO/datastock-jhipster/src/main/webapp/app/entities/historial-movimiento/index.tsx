import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import HistorialMovimiento from './historial-movimiento';
import HistorialMovimientoDetail from './historial-movimiento-detail';
import HistorialMovimientoUpdate from './historial-movimiento-update';
import HistorialMovimientoDeleteDialog from './historial-movimiento-delete-dialog';

const HistorialMovimientoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<HistorialMovimiento />} />
    <Route path="new" element={<HistorialMovimientoUpdate />} />
    <Route path=":id">
      <Route index element={<HistorialMovimientoDetail />} />
      <Route path="edit" element={<HistorialMovimientoUpdate />} />
      <Route path="delete" element={<HistorialMovimientoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default HistorialMovimientoRoutes;
