import {DomainBase} from '../../utils/utils';
import {Auxilio} from "../auxilio/auxilio";

export interface Parcela extends DomainBase {

    data: string;
    datapagamento: string;
    datavencimento: string;
    status: string;
    valor: string;
    valortotal: string;
    valorpago: number;
    auxilio_id: Auxilio;
    parcela: number;
    qtdparcelasquitar: string;

}