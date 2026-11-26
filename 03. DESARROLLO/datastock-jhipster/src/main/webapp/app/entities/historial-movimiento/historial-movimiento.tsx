import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './historial-movimiento.reducer';

export const HistorialMovimiento = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const historialMovimientoList = useAppSelector(state => state.historialMovimiento.entities);
  const loading = useAppSelector(state => state.historialMovimiento.loading);
  const totalItems = useAppSelector(state => state.historialMovimiento.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="historial-movimiento-heading" data-cy="HistorialMovimientoHeading">
        <Translate contentKey="datastockApp.historialMovimiento.home.title">Historial Movimientos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="datastockApp.historialMovimiento.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/historial-movimiento/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="datastockApp.historialMovimiento.home.createLabel">Create new Historial Movimiento</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {historialMovimientoList && historialMovimientoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="datastockApp.historialMovimiento.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('idSitioDestino')}>
                  <Translate contentKey="datastockApp.historialMovimiento.idSitioDestino">Id Sitio Destino</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('idSitioDestino')} />
                </th>
                <th className="hand" onClick={sort('tipoMovimiento')}>
                  <Translate contentKey="datastockApp.historialMovimiento.tipoMovimiento">Tipo Movimiento</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tipoMovimiento')} />
                </th>
                <th className="hand" onClick={sort('cantidad')}>
                  <Translate contentKey="datastockApp.historialMovimiento.cantidad">Cantidad</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cantidad')} />
                </th>
                <th className="hand" onClick={sort('fechaHora')}>
                  <Translate contentKey="datastockApp.historialMovimiento.fechaHora">Fecha Hora</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fechaHora')} />
                </th>
                <th>
                  <Translate contentKey="datastockApp.historialMovimiento.producto">Producto</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="datastockApp.historialMovimiento.sitio">Sitio</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="datastockApp.historialMovimiento.usuario">Usuario</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {historialMovimientoList.map((historialMovimiento, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/historial-movimiento/${historialMovimiento.id}`} color="link" size="sm">
                      {historialMovimiento.id}
                    </Button>
                  </td>
                  <td>{historialMovimiento.idSitioDestino}</td>
                  <td>{historialMovimiento.tipoMovimiento}</td>
                  <td>{historialMovimiento.cantidad}</td>
                  <td>
                    {historialMovimiento.fechaHora ? (
                      <TextFormat type="date" value={historialMovimiento.fechaHora} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {historialMovimiento.producto ? (
                      <Link to={`/producto/${historialMovimiento.producto.id}`}>{historialMovimiento.producto.nombreProducto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {historialMovimiento.sitio ? (
                      <Link to={`/sitio/${historialMovimiento.sitio.id}`}>{historialMovimiento.sitio.nombreSitio}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {historialMovimiento.usuario ? (
                      <Link to={`/usuario/${historialMovimiento.usuario.id}`}>{historialMovimiento.usuario.nombre}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/historial-movimiento/${historialMovimiento.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/historial-movimiento/${historialMovimiento.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/historial-movimiento/${historialMovimiento.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="datastockApp.historialMovimiento.home.notFound">No Historial Movimientos found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={historialMovimientoList && historialMovimientoList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default HistorialMovimiento;
