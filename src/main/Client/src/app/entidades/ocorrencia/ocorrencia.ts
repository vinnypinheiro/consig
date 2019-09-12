import {DomainBase} from '../../utils/utils';

export interface Ocorrencia extends DomainBase {


  data: Date;
  descricao: String;
  usuario_id: number;
  associado_id: number;
  pessoaJuridica_id: number;

}
