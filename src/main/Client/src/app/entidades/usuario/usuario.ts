import {DomainBase} from '../../utils/utils'; 

export interface Usuario extends DomainBase {

      id: number;
     nome: string;
     email: string;
     senha: string;
     login: string;

  dataCadastro: Date;
  dataExpiracao: Date;
  setor: string;


}
