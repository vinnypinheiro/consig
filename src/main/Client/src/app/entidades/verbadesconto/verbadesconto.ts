import {DomainBase} from '../../utils/utils';
import {Auxilio} from "../auxilio/auxilio";
import {Convenio} from "../convenio/convenio";

export interface VerbaDesconto extends DomainBase {

    codigo: string;
    descricao: string;
    convenio_id: Convenio;

}