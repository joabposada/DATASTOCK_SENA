import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import HistorialRolUsuario from './historial-rol-usuario';
import HistorialRolUsuarioDetail from './historial-rol-usuario-detail';
import HistorialRolUsuarioUpdate from './historial-rol-usuario-update';
import HistorialRolUsuarioDeleteDialog from './historial-rol-usuario-delete-dialog';

const HistorialRolUsuarioRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<HistorialRolUsuario />} />
    <Route path="new" element={<HistorialRolUsuarioUpdate />} />
    <Route path=":id">
      <Route index element={<HistorialRolUsuarioDetail />} />
      <Route path="edit" element={<HistorialRolUsuarioUpdate />} />
      <Route path="delete" element={<HistorialRolUsuarioDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default HistorialRolUsuarioRoutes;
