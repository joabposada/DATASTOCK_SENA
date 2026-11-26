import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './lote-entrada.reducer';

export const LoteEntrada = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const loteEntradaList = useAppSelector(state => state.loteEntrada.entities);
  const loading = useAppSelector(state => state.loteEntrada.loading);
  const totalItems = useAppSelector(state => state.loteEntrada.totalItems);

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
      <h2 id="lote-entrada-heading" data-cy="LoteEntradaHeading">
        <Translate contentKey="datastockApp.loteEntrada.home.title">Lote Entradas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="datastockApp.loteEntrada.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/lote-entrada/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="datastockApp.loteEntrada.home.createLabel">Create new Lote Entrada</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {loteEntradaList && loteEntradaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="datastockApp.loteEntrada.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('fechaIngreso')}>
                  <Translate contentKey="datastockApp.loteEntrada.fechaIngreso">Fecha Ingreso</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fechaIngreso')} />
                </th>
                <th className="hand" onClick={sort('fechaVencimiento')}>
                  <Translate contentKey="datastockApp.loteEntrada.fechaVencimiento">Fecha Vencimiento</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fechaVencimiento')} />
                </th>
                <th className="hand" onClick={sort('cantidadIngresada')}>
                  <Translate contentKey="datastockApp.loteEntrada.cantidadIngresada">Cantidad Ingresada</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cantidadIngresada')} />
                </th>
                <th className="hand" onClick={sort('facturaProveedor')}>
                  <Translate contentKey="datastockApp.loteEntrada.facturaProveedor">Factura Proveedor</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('facturaProveedor')} />
                </th>
                <th>
                  <Translate contentKey="datastockApp.loteEntrada.producto">Producto</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="datastockApp.loteEntrada.sitio">Sitio</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {loteEntradaList.map((loteEntrada, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/lote-entrada/${loteEntrada.id}`} color="link" size="sm">
                      {loteEntrada.id}
                    </Button>
                  </td>
                  <td>
                    {loteEntrada.fechaIngreso ? (
                      <TextFormat type="date" value={loteEntrada.fechaIngreso} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {loteEntrada.fechaVencimiento ? (
                      <TextFormat type="date" value={loteEntrada.fechaVencimiento} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{loteEntrada.cantidadIngresada}</td>
                  <td>{loteEntrada.facturaProveedor}</td>
                  <td>
                    {loteEntrada.producto ? (
                      <Link to={`/producto/${loteEntrada.producto.id}`}>{loteEntrada.producto.nombreProducto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{loteEntrada.sitio ? <Link to={`/sitio/${loteEntrada.sitio.id}`}>{loteEntrada.sitio.nombreSitio}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/lote-entrada/${loteEntrada.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/lote-entrada/${loteEntrada.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/lote-entrada/${loteEntrada.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="datastockApp.loteEntrada.home.notFound">No Lote Entradas found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={loteEntradaList && loteEntradaList.length > 0 ? '' : 'd-none'}>
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

export default LoteEntrada;
