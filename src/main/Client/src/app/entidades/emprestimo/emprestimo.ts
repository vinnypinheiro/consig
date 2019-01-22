import {DomainBase} from '../../utils/utils';
import {Parcela} from "../parcela/parcela";

export interface Emprestimo extends DomainBase {

     valor: string;
     datavencimento: string;
     valortotal: string;
     mensalidade: string;
     status: string;
     data: string;
     parcelaList: Parcela;
}