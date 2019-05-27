import {DomainBase} from '../../utils/utils';
import {Correspondente} from "../correspondente/correspondente";

export interface Balanco extends DomainBase {

     associado: string;
     cpf: string;
     associacao: string;
     convenio: string;
     situacaoParcela: string;
     parcela: string;
     vlrParcela: string;
     numeroProposta: string;
     periodo: string;
     
}