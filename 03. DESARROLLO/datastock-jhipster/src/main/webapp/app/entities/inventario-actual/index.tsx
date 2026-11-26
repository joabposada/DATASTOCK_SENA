import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InventarioActual from './inventario-actual';
import InventarioActualDetail from './inventario-actual-detail';
import InventarioActualUpdate from './inventario-actual-update';
import InventarioActualDeleteDialog from './inventario-actual-delete-dialog';

const InventarioActualRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InventarioActual />} />
    <Route path="new" element={<InventarioActualUpdate />} />
    <Route path=":id">
      <Route index element={<InventarioActualDetail />} />
      <Route path="edit" element={<InventarioActualUpdate />} />
      <Route path="delete" element={<InventarioActualDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InventarioActualRoutes;
