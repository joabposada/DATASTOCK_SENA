export interface ISitio {
  id?: number;
  nombreSitio?: string;
  tipoSitio?: string;
  direccion?: string;
}

export const defaultValue: Readonly<ISitio> = {};
