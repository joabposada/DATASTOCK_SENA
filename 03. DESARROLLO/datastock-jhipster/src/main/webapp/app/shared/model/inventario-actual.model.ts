import { IProducto } from 'app/shared/model/producto.model';
import { ISitio } from 'app/shared/model/sitio.model';

export interface IInventarioActual {
  id?: number;
  stockDisponible?: number;
  producto?: IProducto;
  sitio?: ISitio;
}

export const defaultValue: Readonly<IInventarioActual> = {};
