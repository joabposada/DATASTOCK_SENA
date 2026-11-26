import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, FormText, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { getEntities as getRols } from 'app/entities/rol/rol.reducer';
import { createEntity, getEntity, reset, updateEntity } from './historial-rol-usuario.reducer';

export const HistorialRolUsuarioUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const usuarios = useAppSelector(state => state.usuario.entities);
  const rols = useAppSelector(state => state.rol.entities);
  const historialRolUsuarioEntity = useAppSelector(state => state.historialRolUsuario.entity);
  const loading = useAppSelector(state => state.historialRolUsuario.loading);
  const updating = useAppSelector(state => state.historialRolUsuario.updating);
  const updateSuccess = useAppSelector(state => state.historialRolUsuario.updateSuccess);

  const handleClose = () => {
    navigate(`/historial-rol-usuario${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsuarios({}));
    dispatch(getRols({}));
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

    const entity = {
      ...historialRolUsuarioEntity,
      ...values,
      usuario: usuarios.find(it => it.id.toString() === values.usuario?.toString()),
      rol: rols.find(it => it.id.toString() === values.rol?.toString()),
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
          ...historialRolUsuarioEntity,
          usuario: historialRolUsuarioEntity?.usuario?.id,
          rol: historialRolUsuarioEntity?.rol?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="datastockApp.historialRolUsuario.home.createOrEditLabel" data-cy="HistorialRolUsuarioCreateUpdateHeading">
            <Translate contentKey="datastockApp.historialRolUsuario.home.createOrEditLabel">Create or edit a HistorialRolUsuario</Translate>
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
                  id="historial-rol-usuario-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('datastockApp.historialRolUsuario.fechaAsignacion')}
                id="historial-rol-usuario-fechaAsignacion"
                name="fechaAsignacion"
                data-cy="fechaAsignacion"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('datastockApp.historialRolUsuario.estado')}
                id="historial-rol-usuario-estado"
                name="estado"
                data-cy="estado"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                id="historial-rol-usuario-usuario"
                name="usuario"
                data-cy="usuario"
                label={translate('datastockApp.historialRolUsuario.usuario')}
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
              <ValidatedField
                id="historial-rol-usuario-rol"
                name="rol"
                data-cy="rol"
                label={translate('datastockApp.historialRolUsuario.rol')}
                type="select"
                required
              >
                <option value="" key="0" />
                {rols
                  ? rols.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombreRol}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/historial-rol-usuario" replace color="info">
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

export default HistorialRolUsuarioUpdate;
