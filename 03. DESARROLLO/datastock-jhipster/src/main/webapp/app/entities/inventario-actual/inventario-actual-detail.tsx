import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './inventario-actual.reducer';

export const InventarioActualDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const inventarioActualEntity = useAppSelector(state => state.inventarioActual.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="inventarioActualDetailsHeading">
          <Translate contentKey="datastockApp.inventarioActual.detail.title">InventarioActual</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{inventarioActualEntity.id}</dd>
          <dt>
            <span id="stockDisponible">
              <Translate contentKey="datastockApp.inventarioActual.stockDisponible">Stock Disponible</Translate>
            </span>
          </dt>
          <dd>{inventarioActualEntity.stockDisponible}</dd>
          <dt>
            <Translate contentKey="datastockApp.inventarioActual.producto">Producto</Translate>
          </dt>
          <dd>{inventarioActualEntity.producto ? inventarioActualEntity.producto.nombreProducto : ''}</dd>
          <dt>
            <Translate contentKey="datastockApp.inventarioActual.sitio">Sitio</Translate>
          </dt>
          <dd>{inventarioActualEntity.sitio ? inventarioActualEntity.sitio.nombreSitio : ''}</dd>
        </dl>
        <Button tag={Link} to="/inventario-actual" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/inventario-actual/${inventarioActualEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InventarioActualDetail;
