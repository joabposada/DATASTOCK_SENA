import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LoteEntrada from './lote-entrada';
import LoteEntradaDetail from './lote-entrada-detail';
import LoteEntradaUpdate from './lote-entrada-update';
import LoteEntradaDeleteDialog from './lote-entrada-delete-dialog';

const LoteEntradaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LoteEntrada />} />
    <Route path="new" element={<LoteEntradaUpdate />} />
    <Route path=":id">
      <Route index element={<LoteEntradaDetail />} />
      <Route path="edit" element={<LoteEntradaUpdate />} />
      <Route path="delete" element={<LoteEntradaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LoteEntradaRoutes;
