import {DomainBase} from '../../utils/utils';
import {Correspondente} from "../correspondente/correspondente";

export interface ContaReceber extends DomainBase {

     associado: string;
     cpf: string;
     associacao: string;
     convenio: string;
     situacaoContaReceber: string;
     contaReceber: string;
     vlrContaReceber: string;
     numeroProposta: string;
     periodo: string;
     
}
