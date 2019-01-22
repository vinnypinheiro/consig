import {DomainBase} from '../../utils/utils'; 

export interface Usuario extends DomainBase {

  id: number;
  senha: string;
  nome: string;
  email: string;
}
