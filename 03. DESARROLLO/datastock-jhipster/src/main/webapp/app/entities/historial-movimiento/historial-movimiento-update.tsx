import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, FormText, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getProductos } from 'app/entities/producto/producto.reducer';
import { getEntities as getSitios } from 'app/entities/sitio/sitio.reducer';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { createEntity, getEntity, reset, updateEntity } from './historial-movimiento.reducer';

export const HistorialMovimientoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const productos = useAppSelector(state => state.producto.entities);
  const sitios = useAppSelector(state => state.sitio.entities);
  const usuarios = useAppSelector(state => state.usuario.entities);
  const historialMovimientoEntity = useAppSelector(state => state.historialMovimiento.entity);
  const loading = useAppSelector(state => state.historialMovimiento.loading);
  const updating = useAppSelector(state => state.historialMovimiento.updating);
  const updateSuccess = useAppSelector(state => state.historialMovimiento.updateSuccess);

  const handleClose = () => {
    navigate(`/historial-movimiento${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProductos({}));
    dispatch(getSitios({}));
    dispatch(getUsuarios({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.idSitioDestino !== undefined && typeof values.idSitioDestino !== 'number') {
      values.idSitioDestino = Number(values.idSitioDestino);
    }
    if (values.cantidad !== undefined && typeof values.cantidad !== 'number') {
      values.cantidad = Number(values.cantidad);
    }
    values.fechaHora = convertDateTimeToServer(values.fechaHora);

    const entity = {
      ...historialMovimientoEntity,
      ...values,
      producto: productos.find(it => it.id.toString() === values.producto?.toString()),
      sitio: sitios.find(it => it.id.toString() === values.sitio?.toString()),
      usuario: usuarios.find(it => it.id.toString() === values.usuario?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          fechaHora: displayDefaultDateTime(),
        }
      : {
          ...historialMovimientoEntity,
          fechaHora: convertDateTimeFromServer(historialMovimientoEntity.fechaHora),
          producto: historialMovimientoEntity?.producto?.id,
          sitio: historialMovimientoEntity?.sitio?.id,
          usuario: historialMovimientoEntity?.usuario?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="datastockApp.historialMovimiento.home.createOrEditLabel" data-cy="HistorialMovimientoCreateUpdateHeading">
            <Translate contentKey="datastockApp.historialMovimiento.home.createOrEditLabel">Create or edit a HistorialMovimiento</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="historial-movimiento-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('datastockApp.historialMovimiento.idSitioDestino')}
                id="historial-movimiento-idSitioDestino"
                name="idSitioDestino"
                data-cy="idSitioDestino"
                type="text"
              />
              <ValidatedField
                label={translate('datastockApp.historialMovimiento.tipoMovimiento')}
                id="historial-movimiento-tipoMovimiento"
                name="tipoMovimiento"
                data-cy="tipoMovimiento"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('datastockApp.historialMovimiento.cantidad')}
                id="historial-movimiento-cantidad"
                name="cantidad"
                data-cy="cantidad"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('datastockApp.historialMovimiento.fechaHora')}
                id="historial-movimiento-fechaHora"
                name="fechaHora"
                data-cy="fechaHora"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="historial-movimiento-producto"
                name="producto"
                data-cy="producto"
                label={translate('datastockApp.historialMovimiento.producto')}
                type="select"
                required
              >
                <option value="" key="0" />
                {productos
                  ? productos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombreProducto}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="historial-movimiento-sitio"
                name="sitio"
                data-cy="sitio"
                label={translate('datastockApp.historialMovimiento.sitio')}
                type="select"
                required
              >
                <option value="" key="0" />
                {sitios
                  ? sitios.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombreSitio}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="historial-movimiento-usuario"
                name="usuario"
                data-cy="usuario"
                label={translate('datastockApp.historialMovimiento.usuario')}
                type="select"
                required
              >
                <option value="" key="0" />
                {usuarios
                  ? usuarios.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombre}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/historial-movimiento" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default HistorialMovimientoUpdate;
