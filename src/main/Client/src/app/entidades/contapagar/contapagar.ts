import {DomainBase} from '../../utils/utils';
import {Correspondente} from "../correspondente/correspondente";

export interface ContaPagar extends DomainBase {

     associado: string;
     cpf: string;
     associacao: string;
     convenio: string;
     situacaoContaPagar: string;
     contaPagar: string;
     vlrContaPagar: string;
     numeroProposta: string;
     periodo: string;
     
}
