import dayjs from 'dayjs';
import { IUsuario } from 'app/shared/model/usuario.model';
import { IRol } from 'app/shared/model/rol.model';

export interface IHistorialRolUsuario {
  id?: number;
  fechaAsignacion?: dayjs.Dayjs;
  estado?: string;
  usuario?: IUsuario;
  rol?: IRol;
}

export const defaultValue: Readonly<IHistorialRolUsuario> = {};
