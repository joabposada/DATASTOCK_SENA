import { ICategoria } from 'app/shared/model/categoria.model';
import { IMarca } from 'app/shared/model/marca.model';

export interface IProducto {
  id?: number;
  nombreProducto?: string;
  presentacion?: string;
  categoria?: ICategoria;
  marca?: IMarca;
}

export const defaultValue: Readonly<IProducto> = {};
