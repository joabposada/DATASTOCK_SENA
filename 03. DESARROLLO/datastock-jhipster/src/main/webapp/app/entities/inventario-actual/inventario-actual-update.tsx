import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, FormText, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getProductos } from 'app/entities/producto/producto.reducer';
import { getEntities as getSitios } from 'app/entities/sitio/sitio.reducer';
import { createEntity, getEntity, reset, updateEntity } from './inventario-actual.reducer';

export const InventarioActualUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const productos = useAppSelector(state => state.producto.entities);
  const sitios = useAppSelector(state => state.sitio.entities);
  const inventarioActualEntity = useAppSelector(state => state.inventarioActual.entity);
  const loading = useAppSelector(state => state.inventarioActual.loading);
  const updating = useAppSelector(state => state.inventarioActual.updating);
  const updateSuccess = useAppSelector(state => state.inventarioActual.updateSuccess);

  const handleClose = () => {
    navigate(`/inventario-actual${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProductos({}));
    dispatch(getSitios({}));
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
    if (values.stockDisponible !== undefined && typeof values.stockDisponible !== 'number') {
      values.stockDisponible = Number(values.stockDisponible);
    }

    const entity = {
      ...inventarioActualEntity,
      ...values,
      producto: productos.find(it => it.id.toString() === values.producto?.toString()),
      sitio: sitios.find(it => it.id.toString() === values.sitio?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...inventarioActualEntity,
          producto: inventarioActualEntity?.producto?.id,
          sitio: inventarioActualEntity?.sitio?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="datastockApp.inventarioActual.home.createOrEditLabel" data-cy="InventarioActualCreateUpdateHeading">
            <Translate contentKey="datastockApp.inventarioActual.home.createOrEditLabel">Create or edit a InventarioActual</Translate>
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
                  id="inventario-actual-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('datastockApp.inventarioActual.stockDisponible')}
                id="inventario-actual-stockDisponible"
                name="stockDisponible"
                data-cy="stockDisponible"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                id="inventario-actual-producto"
                name="producto"
                data-cy="producto"
                label={translate('datastockApp.inventarioActual.producto')}
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
                id="inventario-actual-sitio"
                name="sitio"
                data-cy="sitio"
                label={translate('datastockApp.inventarioActual.sitio')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/inventario-actual" replace color="info">
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

export default InventarioActualUpdate;
