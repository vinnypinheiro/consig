import {DomainBase} from '../../utils/utils';
import {Associado} from "../associado/associado";
import {Parcela} from "../parcela/parcela";
import {Associacao} from "../associacao/associacao";
import {Convenio} from "../convenio/convenio";
import {Correspondente} from "../correspondente/correspondente";
import {VerbaDesconto} from "../verbadesconto/verbadesconto";

export interface Auxilio extends DomainBase {

    data: Date
    dataContrato: Date;
    numeroproposta: string;
    vlrauxilio: number;
    vlrauxextenso: string;
    qtdparcelas: number;
    vlrparcelas: number;
    porcentagem: number;
    tipo: string;
    getQtdparcelasnaopagas: number;
    getQtdparcelaspagas: number;

    qtdparcelasquitar: string;
    vlrliquidoliberado: number;
    vlrliquidadorefi: number;

    convenio_id: Convenio;
    associacao_id: Associacao;
    parcelaList: Parcela;
    associado_id: Associado;
    correspondente_id: Correspondente;
    verbadesconto_id: VerbaDesconto;



}