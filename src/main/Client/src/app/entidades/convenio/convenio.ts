import {DomainBase} from '../../utils/utils';
import {VerbaDesconto} from "../verbadesconto/verbadesconto";

export interface Convenio extends DomainBase {

    razaosocial: string;
    nomefantasia: string;
    inscricaoestadual: string;
    inscricaomunicipal: string;
    cnpj: string;
    datainicial:  string;
    datafinal:  string;
    verbadescontoList: VerbaDesconto[];

}