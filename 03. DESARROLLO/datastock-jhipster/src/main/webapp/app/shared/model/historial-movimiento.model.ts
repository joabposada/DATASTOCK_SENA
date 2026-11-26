import dayjs from 'dayjs';
import { IProducto } from 'app/shared/model/producto.model';
import { ISitio } from 'app/shared/model/sitio.model';
import { IUsuario } from 'app/shared/model/usuario.model';

export interface IHistorialMovimiento {
  id?: number;
  idSitioDestino?: number | null;
  tipoMovimiento?: string;
  cantidad?: number;
  fechaHora?: dayjs.Dayjs;
  producto?: IProducto;
  sitio?: ISitio;
  usuario?: IUsuario;
}

export const defaultValue: Readonly<IHistorialMovimiento> = {};
