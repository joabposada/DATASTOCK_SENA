export interface IUsuario {
  id?: number;
  numeroDocumento?: string;
  nombre?: string;
  apellido?: string;
  celular?: string;
  correo?: string;
  password?: string;
}

export const defaultValue: Readonly<IUsuario> = {};
