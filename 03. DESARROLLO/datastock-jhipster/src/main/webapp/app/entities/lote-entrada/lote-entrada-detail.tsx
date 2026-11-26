import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './lote-entrada.reducer';

export const LoteEntradaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const loteEntradaEntity = useAppSelector(state => state.loteEntrada.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="loteEntradaDetailsHeading">
          <Translate contentKey="datastockApp.loteEntrada.detail.title">LoteEntrada</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{loteEntradaEntity.id}</dd>
          <dt>
            <span id="fechaIngreso">
              <Translate contentKey="datastockApp.loteEntrada.fechaIngreso">Fecha Ingreso</Translate>
            </span>
          </dt>
          <dd>
            {loteEntradaEntity.fechaIngreso ? (
              <TextFormat value={loteEntradaEntity.fechaIngreso} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="fechaVencimiento">
              <Translate contentKey="datastockApp.loteEntrada.fechaVencimiento">Fecha Vencimiento</Translate>
            </span>
          </dt>
          <dd>
            {loteEntradaEntity.fechaVencimiento ? (
              <TextFormat value={loteEntradaEntity.fechaVencimiento} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="cantidadIngresada">
              <Translate contentKey="datastockApp.loteEntrada.cantidadIngresada">Cantidad Ingresada</Translate>
            </span>
          </dt>
          <dd>{loteEntradaEntity.cantidadIngresada}</dd>
          <dt>
            <span id="facturaProveedor">
              <Translate contentKey="datastockApp.loteEntrada.facturaProveedor">Factura Proveedor</Translate>
            </span>
          </dt>
          <dd>{loteEntradaEntity.facturaProveedor}</dd>
          <dt>
            <Translate contentKey="datastockApp.loteEntrada.producto">Producto</Translate>
          </dt>
          <dd>{loteEntradaEntity.producto ? loteEntradaEntity.producto.nombreProducto : ''}</dd>
          <dt>
            <Translate contentKey="datastockApp.loteEntrada.sitio">Sitio</Translate>
          </dt>
          <dd>{loteEntradaEntity.sitio ? loteEntradaEntity.sitio.nombreSitio : ''}</dd>
        </dl>
        <Button tag={Link} to="/lote-entrada" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/lote-entrada/${loteEntradaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LoteEntradaDetail;
