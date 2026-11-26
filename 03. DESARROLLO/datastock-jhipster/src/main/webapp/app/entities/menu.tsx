import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/sitio">
        <Translate contentKey="global.menu.entities.sitio" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/categoria">
        <Translate contentKey="global.menu.entities.categoria" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/marca">
        <Translate contentKey="global.menu.entities.marca" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/producto">
        <Translate contentKey="global.menu.entities.producto" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/rol">
        <Translate contentKey="global.menu.entities.rol" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/usuario">
        <Translate contentKey="global.menu.entities.usuario" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/historial-rol-usuario">
        <Translate contentKey="global.menu.entities.historialRolUsuario" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/lote-entrada">
        <Translate contentKey="global.menu.entities.loteEntrada" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/inventario-actual">
        <Translate contentKey="global.menu.entities.inventarioActual" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/historial-movimiento">
        <Translate contentKey="global.menu.entities.historialMovimiento" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
