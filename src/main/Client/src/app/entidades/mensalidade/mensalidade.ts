import {DomainBase} from '../../utils/utils';
import {Associado} from "../associado/associado";
import {VerbaDesconto} from "../verbadesconto/verbadesconto";

export interface Mensalidade extends DomainBase {


    dataAssociacao: Date;


    mensalidade: number;
    statuspagamento: string;
    dataprocesamento: Date;
    numeroproposta: string;
    vlrmensalidade: number;
    mesanoref: string;
    vlrauxextenso: string;

    associado_id: Associado;
    verbadesconto_id: VerbaDesconto;

}