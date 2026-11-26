import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sitio.reducer';

export const SitioDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const sitioEntity = useAppSelector(state => state.sitio.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sitioDetailsHeading">
          <Translate contentKey="datastockApp.sitio.detail.title">Sitio</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sitioEntity.id}</dd>
          <dt>
            <span id="nombreSitio">
              <Translate contentKey="datastockApp.sitio.nombreSitio">Nombre Sitio</Translate>
            </span>
          </dt>
          <dd>{sitioEntity.nombreSitio}</dd>
          <dt>
            <span id="tipoSitio">
              <Translate contentKey="datastockApp.sitio.tipoSitio">Tipo Sitio</Translate>
            </span>
          </dt>
          <dd>{sitioEntity.tipoSitio}</dd>
          <dt>
            <span id="direccion">
              <Translate contentKey="datastockApp.sitio.direccion">Direccion</Translate>
            </span>
          </dt>
          <dd>{sitioEntity.direccion}</dd>
        </dl>
        <Button tag={Link} to="/sitio" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sitio/${sitioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SitioDetail;
