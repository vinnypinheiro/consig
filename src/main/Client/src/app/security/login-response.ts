import {Operador} from "../tributos/operador/operador";
import {UnidadeGestora} from "../tributos/unidadegestora/unidadegestora";
import {Entidade} from "../tributos/entidade/entidade";

export class LoginResponse{
  public usuario;
  public data: Date;
  public token: string;

    constructor() {
    }
}
