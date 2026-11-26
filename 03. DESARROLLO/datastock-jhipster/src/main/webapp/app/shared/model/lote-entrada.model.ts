import dayjs from 'dayjs';
import { IProducto } from 'app/shared/model/producto.model';
import { ISitio } from 'app/shared/model/sitio.model';

export interface ILoteEntrada {
  id?: number;
  fechaIngreso?: dayjs.Dayjs;
  fechaVencimiento?: dayjs.Dayjs;
  cantidadIngresada?: number;
  facturaProveedor?: string;
  producto?: IProducto;
  sitio?: ISitio;
}

export const defaultValue: Readonly<ILoteEntrada> = {};
