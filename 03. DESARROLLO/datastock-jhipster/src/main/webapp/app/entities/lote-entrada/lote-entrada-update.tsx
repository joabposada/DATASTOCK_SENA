import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, FormText, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getProductos } from 'app/entities/producto/producto.reducer';
import { getEntities as getSitios } from 'app/entities/sitio/sitio.reducer';
import { createEntity, getEntity, reset, updateEntity } from './lote-entrada.reducer';

export const LoteEntradaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const productos = useAppSelector(state => state.producto.entities);
  const sitios = useAppSelector(state => state.sitio.entities);
  const loteEntradaEntity = useAppSelector(state => state.loteEntrada.entity);
  const loading = useAppSelector(state => state.loteEntrada.loading);
  const updating = useAppSelector(state => state.loteEntrada.updating);
  const updateSuccess = useAppSelector(state => state.loteEntrada.updateSuccess);

  const handleClose = () => {
    navigate(`/lote-entrada${location.search}`);
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
    if (values.cantidadIngresada !== undefined && typeof values.cantidadIngresada !== 'number') {
      values.cantidadIngresada = Number(values.cantidadIngresada);
    }

    const entity = {
      ...loteEntradaEntity,
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
          ...loteEntradaEntity,
          producto: loteEntradaEntity?.producto?.id,
          sitio: loteEntradaEntity?.sitio?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="datastockApp.loteEntrada.home.createOrEditLabel" data-cy="LoteEntradaCreateUpdateHeading">
            <Translate contentKey="datastockApp.loteEntrada.home.createOrEditLabel">Create or edit a LoteEntrada</Translate>
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
                  id="lote-entrada-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('datastockApp.loteEntrada.fechaIngreso')}
                id="lote-entrada-fechaIngreso"
                name="fechaIngreso"
                data-cy="fechaIngreso"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('datastockApp.loteEntrada.fechaVencimiento')}
                id="lote-entrada-fechaVencimiento"
                name="fechaVencimiento"
                data-cy="fechaVencimiento"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('datastockApp.loteEntrada.cantidadIngresada')}
                id="lote-entrada-cantidadIngresada"
                name="cantidadIngresada"
                data-cy="cantidadIngresada"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('datastockApp.loteEntrada.facturaProveedor')}
                id="lote-entrada-facturaProveedor"
                name="facturaProveedor"
                data-cy="facturaProveedor"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                id="lote-entrada-producto"
                name="producto"
                data-cy="producto"
                label={translate('datastockApp.loteEntrada.producto')}
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
                id="lote-entrada-sitio"
                name="sitio"
                data-cy="sitio"
                label={translate('datastockApp.loteEntrada.sitio')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/lote-entrada" replace color="info">
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

export default LoteEntradaUpdate;
