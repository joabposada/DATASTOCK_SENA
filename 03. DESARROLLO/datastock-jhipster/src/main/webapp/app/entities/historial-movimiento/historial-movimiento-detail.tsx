import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './historial-movimiento.reducer';

export const HistorialMovimientoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const historialMovimientoEntity = useAppSelector(state => state.historialMovimiento.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="historialMovimientoDetailsHeading">
          <Translate contentKey="datastockApp.historialMovimiento.detail.title">HistorialMovimiento</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{historialMovimientoEntity.id}</dd>
          <dt>
            <span id="idSitioDestino">
              <Translate contentKey="datastockApp.historialMovimiento.idSitioDestino">Id Sitio Destino</Translate>
            </span>
          </dt>
          <dd>{historialMovimientoEntity.idSitioDestino}</dd>
          <dt>
            <span id="tipoMovimiento">
              <Translate contentKey="datastockApp.historialMovimiento.tipoMovimiento">Tipo Movimiento</Translate>
            </span>
          </dt>
          <dd>{historialMovimientoEntity.tipoMovimiento}</dd>
          <dt>
            <span id="cantidad">
              <Translate contentKey="datastockApp.historialMovimiento.cantidad">Cantidad</Translate>
            </span>
          </dt>
          <dd>{historialMovimientoEntity.cantidad}</dd>
          <dt>
            <span id="fechaHora">
              <Translate contentKey="datastockApp.historialMovimiento.fechaHora">Fecha Hora</Translate>
            </span>
          </dt>
          <dd>
            {historialMovimientoEntity.fechaHora ? (
              <TextFormat value={historialMovimientoEntity.fechaHora} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="datastockApp.historialMovimiento.producto">Producto</Translate>
          </dt>
          <dd>{historialMovimientoEntity.producto ? historialMovimientoEntity.producto.nombreProducto : ''}</dd>
          <dt>
            <Translate contentKey="datastockApp.historialMovimiento.sitio">Sitio</Translate>
          </dt>
          <dd>{historialMovimientoEntity.sitio ? historialMovimientoEntity.sitio.nombreSitio : ''}</dd>
          <dt>
            <Translate contentKey="datastockApp.historialMovimiento.usuario">Usuario</Translate>
          </dt>
          <dd>{historialMovimientoEntity.usuario ? historialMovimientoEntity.usuario.nombre : ''}</dd>
        </dl>
        <Button tag={Link} to="/historial-movimiento" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/historial-movimiento/${historialMovimientoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default HistorialMovimientoDetail;
