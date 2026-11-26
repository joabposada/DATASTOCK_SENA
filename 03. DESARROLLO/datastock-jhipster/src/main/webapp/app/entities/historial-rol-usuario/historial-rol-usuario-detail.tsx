import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './historial-rol-usuario.reducer';

export const HistorialRolUsuarioDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const historialRolUsuarioEntity = useAppSelector(state => state.historialRolUsuario.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="historialRolUsuarioDetailsHeading">
          <Translate contentKey="datastockApp.historialRolUsuario.detail.title">HistorialRolUsuario</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{historialRolUsuarioEntity.id}</dd>
          <dt>
            <span id="fechaAsignacion">
              <Translate contentKey="datastockApp.historialRolUsuario.fechaAsignacion">Fecha Asignacion</Translate>
            </span>
          </dt>
          <dd>
            {historialRolUsuarioEntity.fechaAsignacion ? (
              <TextFormat value={historialRolUsuarioEntity.fechaAsignacion} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="estado">
              <Translate contentKey="datastockApp.historialRolUsuario.estado">Estado</Translate>
            </span>
          </dt>
          <dd>{historialRolUsuarioEntity.estado}</dd>
          <dt>
            <Translate contentKey="datastockApp.historialRolUsuario.usuario">Usuario</Translate>
          </dt>
          <dd>{historialRolUsuarioEntity.usuario ? historialRolUsuarioEntity.usuario.nombre : ''}</dd>
          <dt>
            <Translate contentKey="datastockApp.historialRolUsuario.rol">Rol</Translate>
          </dt>
          <dd>{historialRolUsuarioEntity.rol ? historialRolUsuarioEntity.rol.nombreRol : ''}</dd>
        </dl>
        <Button tag={Link} to="/historial-rol-usuario" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/historial-rol-usuario/${historialRolUsuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default HistorialRolUsuarioDetail;
