import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './usuario.reducer';

export const UsuarioDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const usuarioEntity = useAppSelector(state => state.usuario.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="usuarioDetailsHeading">
          <Translate contentKey="datastockApp.usuario.detail.title">Usuario</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.id}</dd>
          <dt>
            <span id="numeroDocumento">
              <Translate contentKey="datastockApp.usuario.numeroDocumento">Numero Documento</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.numeroDocumento}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="datastockApp.usuario.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.nombre}</dd>
          <dt>
            <span id="apellido">
              <Translate contentKey="datastockApp.usuario.apellido">Apellido</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.apellido}</dd>
          <dt>
            <span id="celular">
              <Translate contentKey="datastockApp.usuario.celular">Celular</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.celular}</dd>
          <dt>
            <span id="correo">
              <Translate contentKey="datastockApp.usuario.correo">Correo</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.correo}</dd>
          <dt>
            <span id="password">
              <Translate contentKey="datastockApp.usuario.password">Password</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.password}</dd>
        </dl>
        <Button tag={Link} to="/usuario" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/usuario/${usuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UsuarioDetail;
